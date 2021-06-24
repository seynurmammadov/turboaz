package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Subscription does not exist!")
public class SubscriptionNotFoundException extends Exception{
    public SubscriptionNotFoundException() {
        super("Subscription does not exist!");
    }
}