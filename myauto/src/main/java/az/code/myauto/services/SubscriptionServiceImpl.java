package az.code.myauto.services;

import az.code.myauto.exceptions.SubscriptionLimitException;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;
import az.code.myauto.repositories.SubscriptionRepo;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    final
    SubscriptionRepo subscriptionRepo;

    public SubscriptionServiceImpl(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @Override
    public List<SubscriptionListDto> getUserSubscriptions(UserData userData) {
        return subscriptionRepo.getUserSubs(userData.getUsername()).stream().map(SubscriptionListDto::new).collect(Collectors.toList());
    }

    @Override
    public SubscriptionListDto addSubscription(UserData userData, SubscriptionDto subscription) {
        if(subscriptionRepo.getUserSubsCount(userData.getUsername())>=5) {
            throw new SubscriptionLimitException();
        }
        return new SubscriptionListDto(subscriptionRepo.save(new Subscription(subscription, userData.getUsername())));
    }

    @Override
    public SubscriptionListDto getSubscriptionById(UserData userData, long id) {
        return null;
    }

    @Override
    public SubscriptionListDto updateSubscriptionById(UserData userData, long id, SubscriptionDto subscription) {
        return null;
    }

    @Override
    public SubscriptionListDto deactiveSubscriptionById(UserData userData, long id) {
        return null;
    }
}
