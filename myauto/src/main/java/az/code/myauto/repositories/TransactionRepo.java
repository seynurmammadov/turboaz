package az.code.myauto.repositories;

import az.code.myauto.models.Transaction;
import az.code.myauto.models.User;
import az.code.myauto.models.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.User.username=:username")
    List<Transaction> getTransactionByUserId(String username);
    List<Transaction> getTransactionsByTransactionType(TransactionType transactionType);
    Transaction getTransactionById(Long id);
}