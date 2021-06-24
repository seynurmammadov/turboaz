package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.models.dtos.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListingService {

    /**
     * This method is for getting listing by id by unregistered user.
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    ListingGetDTO getListingById(long id) throws ListingNotFoundException;

    /**
     * This method is for getting all listings by unregistered user.
     * @param pageable
     * @return
     */
    List<ListingListDTO> getListings(Pageable pageable);

    /**
     * This method is for getting all VIP listings by unregistered user.
     * @param pageable
     * @return
     */
    List<ListingListDTO> getVIPListings(Pageable pageable);

}
