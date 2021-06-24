package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.models.dtos.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListingService {

    ListingGetDTO getListingById(long id) throws ListingNotFoundException;

    List<ListingListDTO> getListings(Pageable pageable);
    List<ListingListDTO> getVIPListings(Pageable pageable);

}
