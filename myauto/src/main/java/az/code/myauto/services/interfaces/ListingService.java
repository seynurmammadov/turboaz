package az.code.myauto.services.interfaces;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;

import java.util.List;

public interface ListingService {
    ListingGetDTO create(ListingCreationDTO listing, UserData user);
    ListingGetDTO update(long id,ListingCreationDTO listing, UserData user);
    void delete(long id, UserData user);

    ListingGetDTO makeVip(long id, UserData user);
    ListingGetDTO makePaid(long id, UserData user);

    ListingGetDTO getById(long id);
    List<ListingListDTO> getListings(Integer pageNo, Integer pageSize, String sortBy);
    List<ListingListDTO> getUserListingsByUsername();

    List<ListingListDTO> getUserListings(UserData userData);
    ListingGetDTO getUserListingById(long id,UserData userData);


}
