package az.code.myauto.daos.interfaces;

import az.code.myauto.models.Transaction;

import java.math.BigDecimal;

public interface TransactionDAO {
    Transaction getTransactionByUsername(String username);

}
