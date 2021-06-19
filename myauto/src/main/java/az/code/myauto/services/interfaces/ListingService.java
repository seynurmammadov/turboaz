package az.code.myauto.services.interfaces;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;

public interface ListingService {
    ListingGetDTO create(ListingCreationDTO listing, UserData user);
}
