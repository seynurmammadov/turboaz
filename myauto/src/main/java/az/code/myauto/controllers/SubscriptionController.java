package az.code.myauto.controllers;

import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.dtos.SubscriptionListDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile/subscriptions")
public class SubscriptionController {

    final
    SubscriptionService subscriptionService;

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("")
    public ResponseEntity<List<SubscriptionListDTO>> getAllSubscriptions(@RequestAttribute UserDTO user) {
        logger.info("Getting all subscriptions by registered user");
        return new ResponseEntity<>(subscriptionService.getSubscriptions(user), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SubscriptionListDTO> addSubscription(@RequestBody SubscriptionDTO subscriptionDto,
                                                               @RequestAttribute UserDTO user) {
        logger.info("Creating subscription by registered user");
        return new ResponseEntity<>(subscriptionService.addSubscription(user, subscriptionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionListDTO> updateSubscription(@RequestBody SubscriptionDTO subscriptionDto,
                                                                  @PathVariable long id, @RequestAttribute UserDTO user) {
        logger.info("Updating subscription (by id) by registered user");
        return new ResponseEntity<>(subscriptionService.updateSubscriptionById(id, subscriptionDto, user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionListDTO> getSubscriptionById(@PathVariable long id, @RequestAttribute UserDTO user) {
        logger.info("Getting subscription (by id) by registered user");
        return new ResponseEntity<>(subscriptionService.getSubscriptionById(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubscriptionById(@PathVariable long id, @RequestAttribute UserDTO user) {
        logger.info("Deleting subscription (by id) by registered user");
        subscriptionService.deleteSubscriptionById(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
