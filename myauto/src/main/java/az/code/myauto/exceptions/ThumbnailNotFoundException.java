package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ThumbnailNotFoundException extends Exception{
    public ThumbnailNotFoundException() {
        super("Thumbnail does not exist");
    }
}
