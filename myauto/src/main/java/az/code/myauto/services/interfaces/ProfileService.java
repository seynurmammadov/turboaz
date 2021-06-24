package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.FreeListingAlreadyPostedException;
import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.dtos.*;
import az.code.myauto.models.enums.ListingType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    /**
     * This method is for creating listing by registered user.
     * @param listing
     * @param user
     * @return
     * @throws FreeListingAlreadyPostedException
     */
    ListingGetDTO create(ListingCreationDTO listing, UserDTO user) throws FreeListingAlreadyPostedException;

    /**
     * This method is for updating listing by registered user.
     * @param id
     * @param listing
     * @param user
     * @return
     * @throws ListingNotFoundException
     */
    ListingGetDTO update(long id, ListingCreationDTO listing, UserDTO user) throws ListingNotFoundException;

    /**
     * This method is for deleting listing by registered user.
     * @param id
     * @param user
     * @throws ListingNotFoundException
     */
    void delete(long id, UserDTO user) throws ListingNotFoundException;


    /**
     * This method is for changing status of listing by registered user
     * if user has sufficient amount of money in balance.
     * @param id
     * @param user
     * @param lisitngType
     * @return
     * @throws ListingNotFoundException
     * @throws TransactionIncorrectAmountException
     * @throws TransactionInsufficientFundsException
     */
    ListingGetDTO updateStatus(long id, UserDTO user, ListingType listingType) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException;

    /**
     * This method is for setting new thumbnail to listing by registered user
     * @param id
     * @param user
     * @param imageDTO
     * @return
     */
    ListingGetDTO setNewThumbnail(long id, UserDTO user, ImageDTO imageDTO);

    /**
     * This method is for getting listings by registered user
     * @param pageable
     * @param user
     * @return
     */
    List<ListingListDTO> getUserListings(Pageable pageable, UserDTO user);

    /**
     * This method is for getting listing by id by registered user
     * @param id
     * @param user
     * @return
     * @throws ListingNotFoundException
     */
    ListingGetDTO getUserListingById(long id, UserDTO user) throws ListingNotFoundException;

    /**
     * This method is for checking if listing exists by registered user
     * @param id
     * @param user
     * @return
     * @throws ListingNotFoundException
     */
    Listing isListingExist(long id, UserDTO user) throws ListingNotFoundException;

}
