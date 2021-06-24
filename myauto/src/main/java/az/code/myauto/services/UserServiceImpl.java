package az.code.myauto.services;

import az.code.myauto.controllers.SubscriptionController;
import az.code.myauto.models.User;
import az.code.myauto.models.dtos.UserRegistrationDTO;
import az.code.myauto.models.mappers.MapperModel;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.services.interfaces.UserConfirmationService;
import az.code.myauto.services.interfaces.UserService;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.admin.resource}")
    private String adminClientId;
    @Value("${app.keycloak.admin.realm}")
    private String adminRealm;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;
    @Value("${app.keycloak.role}")
    private String role;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    @Value("${app.keycloak.username}")
    private String username;
    @Value("${app.keycloak.password}")
    private String password;
    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    final
    UserRepo userRepo;
    final
    MapperModel mapperModel;
    final
    UserConfirmationService userConfirmationService;

    public UserServiceImpl(UserRepo userRepo, MapperModel mapperModel, UserConfirmationService userConfirmationService) {
        this.userRepo = userRepo;
        this.mapperModel = mapperModel;
        this.userConfirmationService = userConfirmationService;
    }

    @Override
    public UserRegistrationDTO registerUser(UserRegistrationDTO userDTO) {

        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm(adminRealm).clientId(adminClientId)
                .username(username).password(password)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
        keycloak.tokenManager().getAccessToken();

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(createKeycloakUser(userDTO));
        userDTO.setStatusCode(response.getStatus());
        userDTO.setStatus(response.getStatusInfo().toString());
        if (response.getStatus() == 201) {
            setPasswordAndRole(userDTO, realmResource, usersResource, response);
            User user = createUser(userDTO);
            userConfirmationService.createVerifyToken(user);
        }
        return userDTO;
    }

    private UserRepresentation createKeycloakUser(UserRegistrationDTO userDTO) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getName());
        user.setLastName(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setAttributes(Collections.singletonMap("phoneNumber", Collections.singletonList(userDTO.getPhone())));
        return user;
    }

    private void setPasswordAndRole(UserRegistrationDTO userDTO,
                                    RealmResource realmResource,
                                    UsersResource usersResource,
                                    Response response) {
        String userId = CreatedResponseUtil.getCreatedId(response);
        logger.info("Created userId {}", userId);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDTO.getPassword());

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(passwordCred);
        RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(realmRoleUser));
    }

    private User createUser(UserRegistrationDTO userDTO) {
        return userRepo.save(userRepo.save(mapperModel.entityToDTO(userDTO, User.class)));
    }
}
