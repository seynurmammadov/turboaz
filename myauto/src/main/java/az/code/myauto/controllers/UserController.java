package az.code.myauto.controllers;

import az.code.myauto.models.dtos.UserRegistrationDTO;
import az.code.myauto.services.interfaces.UserConfirmationService;
import az.code.myauto.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    final
    UserService userService;

    final
    UserConfirmationService userConfirmationService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserConfirmationService userConfirmationService) {
        this.userService = userService;
        this.userConfirmationService = userConfirmationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDTO> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        logger.info("User registration.");
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token") String confirmationToken) {
        userConfirmationService.verifyToken(confirmationToken);
        return new ResponseEntity(HttpStatus.OK);
    }
}
