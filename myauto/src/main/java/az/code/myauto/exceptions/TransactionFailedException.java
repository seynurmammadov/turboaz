package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Transaction was failed!")
public class TransactionFailedException extends RuntimeException {
    public TransactionFailedException() {
        super("Transaction was failed!");
    }
}
