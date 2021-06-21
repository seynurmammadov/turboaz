package az.code.myauto.controllers;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;
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

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionListDto>> getAllSubscriptions(@RequestAttribute UserData user) {
        logger.info("Getting all subscriptions by registered user");
        return new ResponseEntity<>(subscriptionService.getUserSubscriptions(user)  , HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SubscriptionListDto> addSubscription(@RequestBody SubscriptionDto subscriptionDto, @RequestAttribute UserData user) {
        logger.info("Creating subscription by registered user");
        return new ResponseEntity<>(subscriptionService.addSubscription( user,subscriptionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionListDto> updateSubscription(@RequestBody SubscriptionDto subscriptionDto,@PathVariable long id,@RequestAttribute UserData user) {
        logger.info("Updating subscription (by id) by registered user");
        return new ResponseEntity<>(subscriptionService.updateSubscriptionById(id,subscriptionDto,user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionListDto> getSubscriptionById(@PathVariable long id,@RequestAttribute UserData user) {
        logger.info("Getting subscription (by id) by registered user");
        return new ResponseEntity<>(subscriptionService.getSubscriptionById(id,user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubscriptionById(@PathVariable long id,@RequestAttribute UserData user) {
        logger.info("Deleting subscription (by id) by registered user");
        subscriptionService.deleteSubscriptionById(id,user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
