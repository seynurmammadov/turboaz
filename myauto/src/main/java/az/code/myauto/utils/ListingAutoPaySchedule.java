package az.code.myauto.utils;

import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.UserData;
import az.code.myauto.models.enums.ListingType;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ListingAutoPaySchedule {
    @Autowired
    MailSenderUtil mailSenderUtil;
    @Autowired
    ListingRepo listingRepo;
    @Autowired
    TransactionService transactionService;

    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Baku")
    public void expireDateSendMail() {
        List<Listing> allListings = listingRepo.getAllActiveListings();
        for (Listing listing : allListings) {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime oneDayBeforeExpire = listing.getUpdatedAt().plusMonths(1).minusDays(1);
            LocalDateTime paymentDate = listing.getUpdatedAt().plusMonths(1);
            if (today.isAfter(oneDayBeforeExpire)) {
                mailSenderUtil.sendEmail(listing.getUser().getEmail(), "Attention !", "Tomorrow you have to pay for listing  " + listing.getUpdatedAt().plusMonths(1) + " -payment date");
            }
            if (today.isAfter(paymentDate)) {
                UserData userData = new UserData();
                userData.setUsername(listing.getUser().getUsername());
                try {
                    transactionService.decreaseBalance(ListingType.STANDARD.getAmount(), userData, listing.getId());
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
