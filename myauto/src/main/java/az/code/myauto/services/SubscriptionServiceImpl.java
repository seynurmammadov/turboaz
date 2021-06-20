package az.code.myauto.services;

import az.code.myauto.exceptions.SubscriptionLimitException;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;
import az.code.myauto.repositories.SubscriptionRepo;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    final
    SubscriptionRepo subscriptionRepo;

    public SubscriptionServiceImpl(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @Override
    public List<SubscriptionListDto> getUserSubscriptions(UserData userData) {
        return null;
    }

    @Override
    public SubscriptionDto addSubscription(UserData userData, SubscriptionDto subscription) {
        if(subscriptionRepo.getUserSubsCount(userData.getUsername())>=5) {
            throw new SubscriptionLimitException();
        }
        return new SubscriptionDto(subscriptionRepo.save(new Subscription(subscription, userData.getUsername())));
    }

    @Override
    public SubscriptionDto getSubscriptionById(UserData userData, long id) {
        return null;
    }

    @Override
    public SubscriptionDto updateSubscriptionById(UserData userData, long id, SubscriptionDto subscription) {
        return null;
    }

    @Override
    public SubscriptionDto deleteSubscriptionById(UserData userData, long id, SubscriptionDto subscription) {
        return null;
    }
}
