package az.code.myauto.services;

import az.code.myauto.daos.interfaces.ListingDAO;
import az.code.myauto.models.Listing;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    final
    ListingDAO listingDAO;

    public ListingServiceImpl(ListingDAO listingDAO) {
        this.listingDAO = listingDAO;
    }

    @Override
    public ListingGetDTO create(ListingCreationDTO listing, UserData user) {
        return new ListingGetDTO(listingDAO.create(new Listing(listing,user)));
    }

    @Override
    public ListingGetDTO update(long id, ListingCreationDTO listing, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO delete(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO makeVip(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO makePaid(long id, UserData user) {
        return null;
    }

    @Override
    public ListingGetDTO getById(long id) {
        return null;
    }

    @Override
    public List<ListingListDTO> getListings() {
        return null;
    }

    @Override
    public List<ListingListDTO> getUserListingsByUsername() {
        return null;
    }

    @Override
    public List<ListingListDTO> getUserListings(UserData userData) {
        return null;
    }

    @Override
    public ListingGetDTO getUserListingById(long id, UserData userData) {
        return null;
    }


}
