package az.code.myauto.models.mappers;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;

public interface MapperModel {
    <T, Y> T entityToDTO(Y data, Class<T> tClass);

    Listing updateListingToListingDTO(ListingCreationDTO dto, Listing entity);

    Listing listingCreationDTOToListing(ListingCreationDTO dto, Listing entity,UserDTO user);
    Subscription subscriptionDTOToSubscription(SubscriptionDTO dto, Subscription sub);
  
}
