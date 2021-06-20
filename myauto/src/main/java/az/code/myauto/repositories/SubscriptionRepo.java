package az.code.myauto.repositories;

import az.code.myauto.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query("select count(s) from Subscription s WHERE s.User.username=:username")
    int getUserSubsCount(String username);
}
