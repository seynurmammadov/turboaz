package az.code.myauto.controllers;

import az.code.myauto.exceptions.TransactionIncorrectAmountException;
import az.code.myauto.models.dtos.AmountDTO;
import az.code.myauto.models.dtos.TransactionListDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.services.interfaces.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Double> getBalance(@RequestAttribute UserDTO user) {
        logger.info("Getting balance by registered user");

        return new ResponseEntity<>(transactionService.getBalance(user), HttpStatus.OK);
    }

    @GetMapping("/wallet/transactions")
    public ResponseEntity<List<TransactionListDTO>> getTranactions(
            @RequestParam(required = false, defaultValue = "0") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer itemsCount,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestAttribute UserDTO user) {
        logger.info("Getting transactions by registered user");
        return new ResponseEntity<>(transactionService.getTransactions(PageRequest.of(pageNo, itemsCount, Sort.by(sortBy)), user), HttpStatus.OK);
    }

    @PutMapping("/wallet/increase")
    public ResponseEntity<TransactionListDTO> increaseBalance(@RequestAttribute UserDTO user,
                                                              @RequestBody AmountDTO amount) throws TransactionIncorrectAmountException {
        logger.info("Increasing balance by registered user");
        return new ResponseEntity<>(transactionService.increaseBalance(amount.getAmount(), user), HttpStatus.OK);
    }
}
