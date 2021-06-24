package az.code.myauto.services.interfaces;

import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.dtos.UserRegistrationDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface UserService {
    UserRegistrationDTO registerUser(UserRegistrationDTO user);
    AccessTokenResponse login(UserRegistrationDTO userDTO);
}
