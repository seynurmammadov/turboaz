package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import az.code.myauto.models.enums.ListingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ListingRepo extends JpaRepository<Listing, Long> {
    @Modifying
    @Transactional
    @Query("update Listing l set l.isActive=false where l.id=:id")
    Listing deactiveListing(Long id);

    @Query("select l from Listing l where l.id=:id and l.User.username=:username ")
    Optional<Listing> getUserListingById(Long id,String username);

    @Query("select l from Listing l where l.isActive=true")
    Page<Listing> findAllActive(Pageable pageable);

    @Query("select l from Listing l where l.isActive=true and l.type=:type")
    Page<Listing> findAllActiveVIP(Pageable pageable,ListingType type);

    @Query("select l from Listing l where l.User.username=:username")
    Page<Listing> findAllUser(Pageable pageable,String username);

    @Query("select count(l) from Listing l where l.updatedAt >=:dateTime and l.type=:type and l.User.username=:username")
    int getDefaultInMonth(String username, LocalDateTime dateTime, ListingType type);

    @Query("select l from Listing l where l.isActive=true")
    List<Listing> getAllActiveListings();

}
