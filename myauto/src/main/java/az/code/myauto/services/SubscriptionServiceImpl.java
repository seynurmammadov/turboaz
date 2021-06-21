package az.code.myauto.services;

import az.code.myauto.exceptions.ListingNotFoundException;
import az.code.myauto.exceptions.SubscriptionLimitException;
import az.code.myauto.exceptions.SubscriptionNotFoundException;
import az.code.myauto.models.Listing;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;
import az.code.myauto.repositories.SubscriptionRepo;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;
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
        if (subscriptionRepo.getUserSubsCount(userData.getUsername()) >= 5) {
            throw new SubscriptionLimitException();
        }
        return new SubscriptionListDto(subscriptionRepo.save(new Subscription(subscription, userData.getUsername())));
    }

    @Override
    public SubscriptionListDto getSubscriptionById(long id, UserData userData) {
        return new SubscriptionListDto(subsCheck(id, userData));
    }

    @Override
    public SubscriptionListDto updateSubscriptionById(long id, SubscriptionDto subscription, UserData userData) {
        return null;
    }

    @Override
    public SubscriptionListDto deleteSubscriptionById(long id, UserData userData) {
        Subscription subscription = subscriptionRepo.deleteById(id, userData.getUsername());
        return new SubscriptionListDto(subscription);
    }

    public Subscription subsCheck(long id, UserData user) throws SubscriptionNotFoundException {
        Optional<Subscription> subscription = subscriptionRepo.getUserSubById(id, user.getUsername());
        if (subscription.isPresent()) {
            return subscription.get();
        }
        throw new SubscriptionNotFoundException();
    }

}
