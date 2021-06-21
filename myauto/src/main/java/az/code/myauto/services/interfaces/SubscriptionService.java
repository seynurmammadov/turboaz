package az.code.myauto.services.interfaces;

import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.dtos.SubscriptionListDTO;
import az.code.myauto.models.dtos.UserDTO;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionListDTO> getSubscriptions(UserDTO user);

    SubscriptionListDTO addSubscription(UserDTO user, SubscriptionDTO subscription);

    SubscriptionListDTO getSubscriptionById(long id, UserDTO user);

    SubscriptionListDTO updateSubscriptionById(long id, SubscriptionDTO subscription, UserDTO user);

    void deleteSubscriptionById(long id, UserDTO user);
}
