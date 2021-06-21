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
    MailSenderUtil mailSenderUtil;
    final
    ListingRepo listingRepo;
    final
    TransactionService transactionService;

    public ListingSchedule(MailSenderUtil mailSenderUtil, ListingRepo listingRepo, TransactionService transactionService) {
        this.mailSenderUtil = mailSenderUtil;
        this.listingRepo = listingRepo;
        this.transactionService = transactionService;
    }

    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Baku")
    public void listingNotifications() {
        List<Listing> allListings = listingRepo.findAllActiveListings();
        for (Listing listing : allListings) {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime oneDayBeforeExpire = listing.getUpdatedAt().plusMonths(1).minusDays(1);
            LocalDateTime paymentDate = listing.getUpdatedAt().plusMonths(1);
            if (today.isAfter(oneDayBeforeExpire)) {
                mailSenderUtil.sendEmail(listing.getUser().getEmail(), "Attention !", "Tomorrow you have to pay for listing  " + listing.getUpdatedAt().plusMonths(1) + " -payment date");
            }
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
            }
        }
    }

}
