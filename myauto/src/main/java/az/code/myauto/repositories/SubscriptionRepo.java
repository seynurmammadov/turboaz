package az.code.myauto.repositories;

import az.code.myauto.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query("select count(s) from Subscription s WHERE s.User.username=:username")
    int getCountOfUserSubs(String username);

    @Query("select s from Subscription s WHERE s.User.username=:username")
    List<Subscription> getUserSubs(String username);

    @Query("select s from Subscription s WHERE s.User.username=:username and s.id=:id")
    Optional<Subscription> getUserSubById(long id, String username);

    @Modifying
    @Transactional
    @Query("delete from Subscription s where s.id =:id and s.User.username =:username")
    void deleteSubById(long id, String username);
}
