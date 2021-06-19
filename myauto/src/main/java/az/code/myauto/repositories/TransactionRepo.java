package az.code.myauto.repositories;

import az.code.myauto.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Transaction getTransactionByMUser_Username(String username);
}