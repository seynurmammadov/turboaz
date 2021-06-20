package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ListingRepo extends JpaRepository<Listing, Long> {
    @Modifying
    @Query("update Listing l set l.isActive=false where l.id=:id")
    void deleteById(Long id);
    @Query("select l from Listing l where l.id=:id and l.User.username=:username ")
    Optional<Listing> getUserListingById(Long id,String username);
    @Query("select l from Listing l where l.isActive=true")
    Page<Listing> findAllActive(Pageable pageable);
    @Query("select l from Listing l where l.User.username=:username")
    Page<Listing> findAllUser(Pageable pageable,String username);

}
