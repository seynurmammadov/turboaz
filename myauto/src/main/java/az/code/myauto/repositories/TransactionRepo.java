package az.code.myauto.repositories;

import az.code.myauto.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.User.username=:username")
    Page<Transaction> getTransactionsByUserId(Pageable pageable, String username);
}