package az.code.myauto.repositories;

import az.code.myauto.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepo extends JpaRepository<Listing, Long> {

}
