package az.code.myauto.daos.interfaces;

import az.code.myauto.models.Transaction;
import az.code.myauto.repositories.TransactionRepo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionDAOImpl implements TransactionDAO{
    TransactionRepo transactionRepo;

    public TransactionDAOImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction getTransactionByUsername(String username) {
        return transactionRepo.getTransactionByMUser_Username(username);
    }



}
