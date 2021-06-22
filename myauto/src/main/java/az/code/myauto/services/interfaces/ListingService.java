package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.*;

import java.util.List;

public interface ListingService {

    ListingGetDTO getListingById(long id) throws ListingNotFoundException;

    List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy);

    List<ListingListDTO> getVIPListings(Integer pageNo, Integer pageSize, String sortBy);

}
