package az.code.myauto.services;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.User;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.TransactionListDto;
import az.code.myauto.models.enums.TransactionType;
import az.code.myauto.repositories.ListingRepo;
import az.code.myauto.repositories.TransactionRepo;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static az.code.myauto.utils.PaginationUtil.getResult;
import static az.code.myauto.utils.PaginationUtil.preparePage;

@Service
public class TransactionServiceImpl implements TransactionService {
    final
    UserRepo userRepo;

    final
    TransactionRepo transactionRepo;

    final
    JavaMailSender javaMailSender;


    public TransactionServiceImpl(UserRepo userRepo, TransactionRepo transactionRepo, JavaMailSender javaMailSender, ListingRepo listingRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.javaMailSender = javaMailSender;

    }

    @Override
    public TransactionListDto increaseBalance(double amount, UserData userData) throws TransactionIncorrectAmountException {
        if (amount > 0) {
            User user = userRepo.findByUsername(userData.getUsername());
            user.setBalance(user.getBalance() + amount);
            Transaction newTransaction = Transaction.builder()
                    .transactionType(TransactionType.DEBIT)
                    .amount(amount)
                    .createdAt(LocalDateTime.now())
                    .User(User.builder().username(userData.getUsername()).build())
                    .build();

            sendEmail(user.getEmail(), "Balance Increase Notification",
                    "Dear, " + user.getFullname() + ", your balance was increased by the amount of " +
                            amount + ". Now, you have the total " + user.getBalance() + " AZN at your balance. ");

            return new TransactionListDto(transactionRepo.save(newTransaction));
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public TransactionListDto decreaseBalance(double amount, UserData userData, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException {
        if (amount > 0) {
            User user = userRepo.findByUsername(userData.getUsername());
            double balance = user.getBalance() - amount;
            if (balance >= 0) {
                user.setBalance(balance);
                Transaction newTransaction = Transaction.builder()
                        .transactionType(TransactionType.CREDIT)
                        .amount(amount)
                        .createdAt(LocalDateTime.now())
                        .User(User.builder().username(userData.getUsername()).build())
                        .listing(Listing.builder().id(listingId).build())
                        .build();

                sendEmail(user.getEmail(), "Balance Decrease Notification",
                        "Dear, " + user.getFullname() + ", your balance was decreased by the amount of " +
                                amount + ". Now, you have the total " + user.getBalance() + " AZN at your balance. ");

                return new TransactionListDto(transactionRepo.save(newTransaction));
            }
            throw new TransactionInsufficientFundsException();
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public List<TransactionListDto> getTransactions(Integer pageNo, Integer pageSize, String sortBy, UserData userData) {
        Pageable pageable = preparePage(pageNo, pageSize, sortBy);
        Page<Transaction> pages = transactionRepo.getTransactionsById(pageable, userData.getUsername());
        return getResult(pages.map(TransactionListDto::new));
    }

    @Override
    public double getBalance(UserData userData) {
        return userRepo.findByUsername(userData.getUsername()).getBalance();
    }

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }
}
