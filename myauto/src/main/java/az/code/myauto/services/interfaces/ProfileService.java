package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    ListingGetDTO create(ListingCreationDTO listing, UserDTO user) throws FreeListingAlreadyPostedException;

    ListingGetDTO update(long id, ListingCreationDTO listing, UserDTO user) throws ListingNotFoundException;

    void delete(long id, UserDTO user) throws ListingNotFoundException;

    ListingGetDTO makeVip(long id, UserDTO user) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException;

    ListingGetDTO makePaid(long id, UserDTO user);

    ListingGetDTO setNewThumbnail(long id, UserDTO user, ImageDTO imageDTO);

    List<ListingListDTO> getUserListings(Pageable pageable, UserDTO user);

    ListingGetDTO getUserListingById(long id, UserDTO user) throws ListingNotFoundException;

    Listing isListingExist(long id, UserDTO user) throws ListingNotFoundException;

}