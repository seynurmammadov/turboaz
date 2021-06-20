package az.code.myauto.services.interfaces;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingListDTO;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionListDto> getUserSubscriptions(Integer pageNo, Integer pageSize, String sortBy, UserData userData);
    SubscriptionDto addSubscription(UserData userData, SubscriptionDto subscription);
    SubscriptionDto getSubscriptionById(UserData userData, long id);
    SubscriptionDto updateSubscriptionById(UserData userData, long id, SubscriptionDto subscription);
}
