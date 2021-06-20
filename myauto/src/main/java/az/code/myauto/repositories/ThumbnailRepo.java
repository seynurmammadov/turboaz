package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import az.code.myauto.models.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThumbnailRepo extends JpaRepository<Thumbnail, Long> {
    @Query("select t from Thumbnail t where t.listing =: id")
    Optional<List<Thumbnail>> findThumbnailByListingId(Long id);
}
