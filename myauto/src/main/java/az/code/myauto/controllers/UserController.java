package az.code.myauto.controllers;

import az.code.myauto.models.User;
import az.code.myauto.models.UserConfirmationToken;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.repositories.UserConfirmationTokenRepo;
import az.code.myauto.repositories.UserRepo;
import az.code.myauto.utils.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserConfirmationTokenRepo userConfirmationTokenRepo;
    @Autowired
    MailSenderUtil mailSenderUtil;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> insertUser(@RequestBody User registration){
        User user = userRepo.save(registration);
        UserConfirmationToken confirmationToken = new UserConfirmationToken(user);
        mailSenderUtil.sendEmail(user.getEmail(), "Complete Registration!", "To confirm your account, please click here : " + "http://localhost:8000/api/v1/user/confirm-account?token=" + confirmationToken.getConfirmationToken());
        userConfirmationTokenRepo.save(confirmationToken);
        UserDTO userGetDto = new UserDTO(user);
        return new ResponseEntity<>(userGetDto, HttpStatus.OK);
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token")String confirmationToken){
        UserConfirmationToken token = userConfirmationTokenRepo.findByConfirmationToken(confirmationToken);
        User user = userRepo.findUserByEmail(token.getUser().getEmail());
        if(token != null)
        {
            user.setActive(true);
            userRepo.save(user);
            mailSenderUtil.sendEmail(user.getEmail(), "Congratulations","You have registered successfully");
        }
        else
        {
//            "The link is invalid or broken!
            mailSenderUtil.sendEmail(user.getEmail(), "Attention !","The link is invalid or broken! Please try again");
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
