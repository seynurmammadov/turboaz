package az.code.myauto.services;

import az.code.myauto.daos.interfaces.ListingDAO;
import az.code.myauto.models.Listing;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.services.interfaces.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
