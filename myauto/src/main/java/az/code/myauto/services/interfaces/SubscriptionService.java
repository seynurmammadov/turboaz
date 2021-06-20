package az.code.myauto.services.interfaces;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionListDto> getUserSubscriptions(UserData userData);

    SubscriptionListDto addSubscription(UserData userData, SubscriptionDto subscription);

    SubscriptionListDto getSubscriptionById( long id,UserData userData);

    SubscriptionListDto updateSubscriptionById( long id, SubscriptionDto subscription,UserData userData);

    SubscriptionListDto deactiveSubscriptionById( long id,UserData userData);
}
