package az.code.myauto.daos.interfaces;

import az.code.myauto.models.Transaction;
import az.code.myauto.models.enums.TransactionType;
import az.code.myauto.repositories.TransactionRepo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TransactionDAOImpl implements TransactionDAO{
    TransactionRepo transactionRepo;

    public TransactionDAOImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public List<Transaction> getTransactionByUsername(String username) {
        return transactionRepo.getTransactionsByMUser_Username(username);
    }

    @Override
    public List<Transaction> getTransactionByType(TransactionType transactionType) {
        return transactionRepo.getTransactionsByTransactionType(transactionType);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepo.getTransactionById(id);
    }


}
