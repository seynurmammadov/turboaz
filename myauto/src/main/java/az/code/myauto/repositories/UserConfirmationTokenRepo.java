package az.code.myauto.repositories;

import az.code.myauto.models.UserConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface UserConfirmationTokenRepo extends CrudRepository<UserConfirmationToken, String> {
    UserConfirmationToken findByConfirmationToken(String confirmationToken);
}
