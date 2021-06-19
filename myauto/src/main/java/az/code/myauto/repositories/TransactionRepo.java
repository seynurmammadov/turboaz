package az.code.myauto.repositories;

import az.code.myauto.models.Transaction;
import az.code.myauto.models.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionsByMUser_Username(String username);
    List<Transaction> getTransactionsByTransactionType(TransactionType transactionType);
    Transaction getTransactionById(Long id);
}