package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import az.code.myauto.models.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ThumbnailRepo extends JpaRepository<Thumbnail, Long> {
    @Query("select t from Thumbnail t where t.listing.id =:id")
    Optional<List<Thumbnail>> findThumbnailsByListingId(Long id);
    @Query("select t from Thumbnail t where t.listing.id =:listingId and t.id=:id")
    Optional<Thumbnail> findThumbById(Long listingId,Long id);
    @Modifying
    @Transactional
    @Query("delete from Thumbnail t where t.listing.id =:listingId and t.id=:id")
    void deleteThumbById(Long listingId,Long id);
}
