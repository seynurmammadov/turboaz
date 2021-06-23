package az.code.myauto.models.mappers;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;

public interface MapperModel {
    <T, Y> T entityToDTO(Y data, Class<T> tClass);

    Listing updateListDTOToList(ListingCreationDTO dto, Listing entity);

    Listing createListDTOToList(ListingCreationDTO dto, Listing entity, UserDTO user);
    Subscription updateSubDTOToSub(SubscriptionDTO dto, Subscription sub);
    Subscription createSubDTOToSub(SubscriptionDTO dto, Subscription sub,UserDTO user);

}
