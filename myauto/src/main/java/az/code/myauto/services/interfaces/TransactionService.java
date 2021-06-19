package az.code.myauto.services.interfaces;

import az.code.myauto.models.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    void increaseBalance(BigDecimal amount);
    void decreaseBalance(BigDecimal amount);

}
