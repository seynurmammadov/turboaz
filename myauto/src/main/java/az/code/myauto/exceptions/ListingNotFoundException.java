package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Listing does not exist!")
public class ListingNotFoundException extends RuntimeException{
    public ListingNotFoundException() {
        super("Listing does not exist!");
    }
}
