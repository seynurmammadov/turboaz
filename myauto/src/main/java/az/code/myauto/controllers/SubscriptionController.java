package az.code.myauto.controllers;

import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.SubscriptionDto;
import az.code.myauto.models.dtos.SubscriptionListDto;
import az.code.myauto.services.interfaces.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable long id) {
        logger.info("Updating subscription (by id) by registered user");
        //TODO after updating SubscriptionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable long id) {
        logger.info("Getting subscription (by id) by registered user");
        //TODO after updating SubscriptionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriptionDto> deleteSubscriptionById(@PathVariable long id) {
        logger.info("Deleting subscription (by id) by registered user");
        //TODO after updating SubscriptionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
