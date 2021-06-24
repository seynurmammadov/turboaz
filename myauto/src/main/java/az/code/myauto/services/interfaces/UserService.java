package az.code.myauto.services.interfaces;

import az.code.myauto.models.dtos.UserRegistrationDTO;

public interface UserService {
    UserRegistrationDTO registerUser(UserRegistrationDTO user);
}
