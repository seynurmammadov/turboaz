package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Free(Default) listing already posted in this month!")
public class FreeListingAlreadyPostedException extends RuntimeException{
}