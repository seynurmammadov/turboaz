package az.code.myauto.daos.interfaces;

import az.code.myauto.models.Transaction;
import az.code.myauto.models.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDAO {
    List<Transaction> getTransactionByUsername(String username);
    List<Transaction> getTransactionByType(TransactionType transactionType);
    Transaction getTransactionById(Long id);

}
