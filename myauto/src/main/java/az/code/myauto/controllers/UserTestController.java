package az.code.myauto.controllers;

import az.code.myauto.models.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class UserTestController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUser(@RequestAttribute UserData user) {
        return ResponseEntity.ok(user.toString());
    }
}