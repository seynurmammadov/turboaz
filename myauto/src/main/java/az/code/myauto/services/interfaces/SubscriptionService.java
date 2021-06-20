package az.code.myauto.services.interfaces;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionListDto> getUserSubscriptions(UserData userData);

    SubscriptionListDto addSubscription(UserData userData, SubscriptionDto subscription);

    SubscriptionListDto getSubscriptionById(UserData userData, long id);

    SubscriptionListDto updateSubscriptionById(UserData userData, long id, SubscriptionDto subscription);

    SubscriptionListDto deactiveSubscriptionById(UserData userData, long id);
}
