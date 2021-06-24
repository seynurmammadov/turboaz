package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Insufficient funds in user balance!")
public class TransactionInsufficientFundsException  extends RuntimeException{
    public TransactionInsufficientFundsException() {
        super("Insufficient funds in user balance!");
    }
}