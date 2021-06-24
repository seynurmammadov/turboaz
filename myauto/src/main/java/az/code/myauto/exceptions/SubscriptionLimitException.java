package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "You can have only 5 subscription at the same time!")
public class SubscriptionLimitException extends RuntimeException{
    public SubscriptionLimitException() {
        super("You can have only 5 subscription at the same time!");
    }
}