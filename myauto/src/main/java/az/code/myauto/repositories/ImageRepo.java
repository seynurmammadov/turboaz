package az.code.myauto.repositories;

import az.code.myauto.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    @Query("select t from Image t where t.listing.id =:id")
    Optional<List<Image>> findListingImagesById(Long id);

    @Query("select t from Image t where t.listing.id =:listingId and t.id=:id")
    Optional<Image> findImageById(Long listingId, Long id);

    @Modifying
    @Transactional
    @Query("delete from Image t where t.listing.id =:listingId and t.id=:id")
    void deleteImageByid(Long listingId, Long id);
}
