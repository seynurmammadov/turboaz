package az.code.myauto.services.interfaces;

import az.code.myauto.models.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction increaseBalance(BigDecimal amount);
}
