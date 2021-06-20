package az.code.myauto.services.interfaces;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.exceptions.TransactionInsufficientFundsException;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;

import java.util.List;

public interface ListingService {
    ListingGetDTO create(ListingCreationDTO listing, UserData user);
    ListingGetDTO update(long id,ListingCreationDTO listing, UserData user) throws ListingNotFoundException;
    void delete(long id, UserData user) throws ListingNotFoundException;

    ListingGetDTO makeVip(long id, UserData user) throws ListingNotFoundException, TransactionIncorrectAmountException, TransactionInsufficientFundsException;
    ListingGetDTO makePaid(long id, UserData user);

    ListingGetDTO getById(long id) throws ListingNotFoundException;
    List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy);

    List<ListingListDTO> getUserListings(Integer pageNo, Integer pageSize, String sortBy,UserData userData);
    ListingGetDTO getUserListingById(long id,UserData userData) throws ListingNotFoundException;


}
