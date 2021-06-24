package az.code.myauto.services.interfaces;

import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.dtos.SubscriptionListDTO;
import az.code.myauto.models.dtos.UserDTO;

import java.util.List;

public interface SubscriptionService {

    /**
     * This method is for getting all subscriptions of registered user.
     * @param user
     * @return
     */
    List<SubscriptionListDTO> getSubscriptions(UserDTO user);

    /**
     * This method is for adding new subscription to registered user.
     * @param user
     * @param subscription
     * @return
     */
    SubscriptionListDTO addSubscription(UserDTO user, SubscriptionDTO subscription);

    /**
     * This method is for getting subscription of registered user by id.
     * @param id
     * @param user
     * @return
     */
    SubscriptionListDTO getSubscriptionById(long id, UserDTO user);

    /**
     * This method is for updating subscription of registered user by id.
     * @param id
     * @param subscription
     * @param user
     * @return
     */
    SubscriptionListDTO updateSubscriptionById(long id, SubscriptionDTO subscription, UserDTO user);

    /**
     * This method is for deleting subscription of registered user by id.
     * @param id
     * @param user
     */
    void deleteSubscriptionById(long id, UserDTO user);
}
