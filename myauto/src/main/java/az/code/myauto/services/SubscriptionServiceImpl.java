package az.code.myauto.services;

import az.code.myauto.exceptions.SubscriptionLimitException;
import az.code.myauto.exceptions.SubscriptionNotFoundException;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.dtos.SubscriptionListDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.SubscriptionRepo;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    final
    SubscriptionRepo subscriptionRepo;

    final
    MapperModel mapperModel;

    public SubscriptionServiceImpl(SubscriptionRepo subscriptionRepo, MapperModel mapperModel) {
        this.subscriptionRepo = subscriptionRepo;
        this.mapperModel = mapperModel;
    }

    @Override
    public List<SubscriptionListDTO> getSubscriptions(UserDTO user) {
        return subscriptionRepo.getUserSubs(user.getUsername()).stream()
                .map(r -> mapperModel.entityToDTO(r, SubscriptionListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionListDTO addSubscription(UserDTO user, SubscriptionDTO subscription) {
        if (subscriptionRepo.getCountOfUserSubs(user.getUsername()) >= 5) {
            throw new SubscriptionLimitException();
        }
        return mapperModel.entityToDTO(subscriptionRepo.save(new Subscription(subscription, user.getUsername())), SubscriptionListDTO.class);
    }

    @Override
    public SubscriptionListDTO getSubscriptionById(long id, UserDTO user) {
        return mapperModel.entityToDTO(subsCheck(id, user),SubscriptionListDTO.class);
    }

    @Override
    public SubscriptionListDTO updateSubscriptionById(long id, SubscriptionDTO subscription, UserDTO user) {
        return null;
    }

    @Override
    public void deleteSubscriptionById(long id, UserDTO user) {
        subsCheck(id, user);
        subscriptionRepo.deleteSubById(id, user.getUsername());
    }

    public Subscription subsCheck(long id, UserDTO user) throws SubscriptionNotFoundException {
        Optional<Subscription> subscription = subscriptionRepo.getUserSubById(id, user.getUsername());
        if (subscription.isPresent()) {
            return subscription.get();
        }
        throw new SubscriptionNotFoundException();
    }

}
