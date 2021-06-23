package az.code.myauto.services;

import az.code.myauto.models.User;
import az.code.myauto.models.UserConfirmationToken;
import az.code.myauto.repositories.UserConfirmationTokenRepo;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.services.interfaces.UserConfirmationService;
import az.code.myauto.utils.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserConfirmationServiceImpl implements UserConfirmationService {

    final
    UserConfirmationTokenRepo userConfirmationTokenRepo;

    final
    MessageUtil messageUtil;
    final
    UserRepo userRepo;
    public UserConfirmationServiceImpl(UserConfirmationTokenRepo userConfirmationTokenRepo, MessageUtil messageUtil, UserRepo userRepo) {
        this.userConfirmationTokenRepo = userConfirmationTokenRepo;
        this.messageUtil = messageUtil;
        this.userRepo = userRepo;
    }

    @Override
    public void createVerifyToken(User user){
        String token = userConfirmationTokenRepo
                            .save(UserConfirmationToken.builder().user(user).confirmationToken(UUID.randomUUID().toString()).build())
                            .getConfirmationToken();
        messageUtil.regVerifyNotification(user,token);
    }

    @Override
    public void verifyToken(String token) {
            UserConfirmationToken dbToken = userConfirmationTokenRepo.findByConfirmationToken(token);
            User user = userRepo.findUserByEmail(dbToken.getUser().getEmail());
            user.setIsActive(true);
            userRepo.save(user);
            userConfirmationTokenRepo.delete(dbToken);
            messageUtil.successRegister(user);
    }
}
