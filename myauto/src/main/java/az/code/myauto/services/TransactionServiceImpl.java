package az.code.myauto.services;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.User;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.TransactionListDto;
import az.code.myauto.models.enums.TransactionType;
import az.code.myauto.repositories.TransactionRepo;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.services.interfaces.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
    final
    UserRepo userRepo;

    final
    TransactionRepo transactionRepo;

    public TransactionServiceImpl(UserRepo userRepo, TransactionRepo transactionRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
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
           return new TransactionListDto(transactionRepo.save(newTransaction));
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public TransactionListDto decreaseBalance(double amount, UserData userData, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException {
        if (amount > 0) {
            User user = userRepo.findByUsername(userData.getUsername());
            double balance =user.getBalance() - amount;
            if(balance>=0){
                user.setBalance(balance);
                Transaction newTransaction = Transaction.builder()
                        .transactionType(TransactionType.CREDIT)
                        .amount(amount)
                        .createdAt(LocalDateTime.now())
                        .User(User.builder().username(userData.getUsername()).build())
                        .listing(Listing.builder().id(listingId).build())
                        .build();
               return new TransactionListDto(transactionRepo.save(newTransaction));
            }
            throw new TransactionInsufficientFundsException();
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public double getBalance(UserData userData) {
        return userRepo.findByUsername(userData.getUsername()).getBalance();
    }
}
