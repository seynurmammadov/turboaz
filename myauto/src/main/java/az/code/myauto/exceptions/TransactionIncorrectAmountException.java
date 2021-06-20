package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TransactionIncorrectAmountException extends Exception{
    public TransactionIncorrectAmountException() {
        super("Incorrect amount value!");
    }
}
