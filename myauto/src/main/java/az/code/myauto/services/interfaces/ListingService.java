package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.ThumbnailDTO;

import java.util.List;

public interface ListingService {
    ListingGetDTO create(ListingCreationDTO listing, UserData user) throws FreeListingAlreadyPostedException;
    ListingGetDTO update(long id,ListingCreationDTO listing, UserData user) throws ListingNotFoundException;
    ListingGetDTO delete(long id, UserData user) throws ListingNotFoundException;
    ListingGetDTO makeVip(long id, UserData user) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException;
    ListingGetDTO makePaid(long id, UserData user);
    ListingGetDTO setNewThumbnail(long id, UserData user, ThumbnailDTO thumbnailDTO);
    List<ListingListDTO> getUserListings(Integer pageNo, Integer pageSize, String sortBy,UserData user);
    ListingGetDTO getUserListingById(long id,UserData user) throws ListingNotFoundException;


    ListingGetDTO getById(long id) throws ListingNotFoundException;
    List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy);
    List<ListingListDTO> getVIPListings(Integer pageNo, Integer pageSize, String sortBy);

     Listing listingCheck(long id, UserData user) throws ListingNotFoundException;


}
