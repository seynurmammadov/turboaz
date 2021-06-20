package az.code.myauto.controllers;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.models.Transaction;
import az.code.myauto.models.UserData;
import az.code.myauto.services.interfaces.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class TransactionController {
    final
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    Logger logger = LoggerFactory.getLogger(TransactionController.class);



    @GetMapping("/wallet")
    public ResponseEntity<Double> getBalance(@RequestAttribute UserData user) {
        logger.info("Getting balance by registered user");

        return new ResponseEntity<>(transactionService.getBalance(user), HttpStatus.OK);
    }

    @PutMapping("/wallet/increase")
    public ResponseEntity<Transaction> increaseBalance(@RequestAttribute UserData user,
                                                       @RequestParam Double amount ) throws TransactionIncorrectAmountException {
        logger.info("Increasing balance by registered user");

        return new ResponseEntity<>(transactionService.increaseBalance(amount, user), HttpStatus.OK);
    }


}
