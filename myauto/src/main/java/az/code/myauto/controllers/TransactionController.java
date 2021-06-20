package az.code.myauto.controllers;

import az.code.myauto.models.Transaction;
import az.code.myauto.models.UserData;
import az.code.myauto.models.dtos.SubscriptionListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping("/wallet")
    public ResponseEntity<Double> getBalance(@RequestAttribute UserData user) {
        logger.info("Getting balance by registered user");
        //TODO after updating TransactionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/wallet/increase")
    public ResponseEntity<Transaction> increaseBalance(@RequestAttribute UserData user,
                                                       @RequestParam Double amount ) {
        logger.info("Getting balance by registered user");
        //TODO after updating TransactionService, 'null' must be changed.
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
