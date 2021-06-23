package az.code.myauto.services.interfaces;

import az.code.myauto.models.User;
import az.code.myauto.models.UserConfirmationToken;

public interface UserConfirmationService {
    void createVerifyToken(User user);
    void verifyToken(String token);
}
