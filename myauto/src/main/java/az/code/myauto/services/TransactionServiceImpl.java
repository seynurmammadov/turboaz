package az.code.myauto.services;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.User;
import az.code.myauto.models.dtos.TransactionListDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.enums.TransactionType;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.TransactionRepo;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.services.interfaces.TransactionService;

import az.code.myauto.utils.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static az.code.myauto.utils.BaseUtils.paginationResult;

@Service
public class TransactionServiceImpl implements TransactionService {
    final
    UserRepo userRepo;

    final
    TransactionRepo transactionRepo;

    final
    MessageUtil messageUtil;

    final
    MapperModel mapper;


        
    public TransactionServiceImpl(UserRepo userRepo, TransactionRepo transactionRepo, MessageUtil messageUtil, MapperModel mapper) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.messageUtil = messageUtil;
        this.mapper = mapper;
    }

    @Override
    public TransactionListDTO increaseBalance(double amount, UserDTO userData) throws TransactionIncorrectAmountException {
        if (amount > 0) {
            User user = userRepo.findUserByUsername(userData.getUsername());
            user.setBalance(user.getBalance() + amount);
            Transaction newTransaction = Transaction.builder()
                    .transactionType(TransactionType.DEBIT)
                    .amount(amount)
                    .createdAt(LocalDateTime.now())
                    .User(User.builder().username(userData.getUsername()).build())
                    .build();

            messageUtil.sendNotification(newTransaction.getTransactionType().getOperationName(), user, amount);
            userRepo.save(user);
            return mapper.entityToDTO(transactionRepo.save(newTransaction),TransactionListDTO.class);
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public TransactionListDTO decreaseBalance(double amount, UserDTO userData, long listingId) throws TransactionIncorrectAmountException, TransactionInsufficientFundsException {
        if (amount > 0) {
            User user = userRepo.findUserByUsername(userData.getUsername());
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

                messageUtil.sendNotification(newTransaction.getTransactionType().getOperationName(), user, amount);
                userRepo.save(user);
                return mapper.entityToDTO(transactionRepo.save(newTransaction),TransactionListDTO.class);
            }
            throw new TransactionInsufficientFundsException();
        }
        throw new TransactionIncorrectAmountException();
    }

    @Override
    public List<TransactionListDTO> getTransactions(Pageable pageable, UserDTO userData) {
        Page<Transaction> pages = transactionRepo.getTransactionsByUserId(pageable, userData.getUsername());
        return paginationResult(pages.map(t->mapper.entityToDTO(t,TransactionListDTO.class)));
    }

    @Override
    public double getBalance(UserDTO userData) {
        return userRepo.findUserByUsername(userData.getUsername()).getBalance();
    }

}
