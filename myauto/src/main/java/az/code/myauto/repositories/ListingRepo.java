package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListingRepo extends JpaRepository<Listing, Long> {
    @Query("update Listing l set l.id=:id ")
    void deleteById(Long id);
    @Query("select l from Listing l where l.id=:id and l.User.username=username")
    Listing getUserListinbById(Long id,String username);
}
