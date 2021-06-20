package az.code.myauto.controllers;

import az.code.myauto.models.dtos.ListingGetDTO;
import az.code.myauto.models.dtos.SubscriptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile/subscriptions")
public class SubscriptionController {

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        logger.info("Getting all subscriptions by registered user");
        //TODO after updating SubscriptionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SubscriptionDto> addSubscription() {
        logger.info("Creating subscription by registered user");
        //TODO after updating SubscriptionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.CREATED);
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
