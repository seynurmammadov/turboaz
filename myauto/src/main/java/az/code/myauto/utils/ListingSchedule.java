package az.code.myauto.utils;

import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.enums.ListingType;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ListingSchedule {
    final
    MessageUtil messageUtil;
    final
    ListingRepo listingRepo;
    final
    TransactionService transactionService;

    public ListingSchedule(MessageUtil messageUtil, ListingRepo listingRepo, TransactionService transactionService) {
        this.messageUtil = messageUtil;
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
    }

    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Baku")
    public void listingNotifications() {
        List<Listing> allActiveListingsBetweenDates = listingRepo.findAllActiveListingsBetweenDate(LocalDateTime.now().minusMonths(1).minusDays(1), LocalDateTime.now().minusMonths(1).plusDays(1));
        for (Listing listing : allActiveListingsBetweenDates) {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime oneDayBeforeExpire = listing.getUpdatedAt().plusMonths(1).minusDays(1);
            LocalDateTime paymentDate = listing.getUpdatedAt().plusMonths(1);
            if (today.isAfter(paymentDate)) {
                UserDTO user = UserDTO.builder().username(listing.getUser().getUsername()).build();
                try {
                    transactionService.decreaseBalance(ListingType.STANDARD.getAmount(), user, listing.getId());
                    listing.setUpdatedAt(today);
                    listingRepo.save(listing);
                } catch (Exception e) {
                    listing.setActive(false);
                    listingRepo.save(listing);
                }
            } else if (today.isAfter(oneDayBeforeExpire)) {
                messageUtil.autoPaymentNotification(listing.getUser().getEmail(), listing.getUpdatedAt().plusMonths(1));
            }
        }
    }
}
