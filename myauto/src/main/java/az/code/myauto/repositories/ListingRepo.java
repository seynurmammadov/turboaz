package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import az.code.myauto.models.enums.ListingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ListingRepo extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {
    @Modifying
    @Transactional
    @Query("update Listing l set l.isActive=false where l.id=:id")
    void deactivateListing(Long id);

    @Query("select l from Listing l where l.id=:id and l.User.username=:username ")
    Optional<Listing> getUserListingById(Long id, String username);

    @Query("select l from Listing l where l.isActive=true")
    Page<Listing> findAllActiveListings(Pageable pageable);

    @Query("select l from Listing l where l.isActive=true and l.type=:type")
    Page<Listing> findAllActiveVIPListings(Pageable pageable, ListingType type);

    @Query("select l from Listing l where l.User.username=:username")
    Page<Listing> findAllUserListings(Pageable pageable, String username);

    @Query("select count(l) from Listing l where l.updatedAt >=:dateTime and l.type=:type and l.User.username=:username")
    int countOfDefaultUserListings(String username, LocalDateTime dateTime, ListingType type);

    @Query("select l from Listing l where l.isActive=true")
    List<Listing> findAllActiveListings();

    @Query("select l from Listing l where l.isActive=true and l.updatedAt >= :first_date and l.updatedAt <= :second_date")
    List<Listing> findAllActiveListingsBetweenDate ( LocalDateTime first_date,
                                                     LocalDateTime second_date );
}
