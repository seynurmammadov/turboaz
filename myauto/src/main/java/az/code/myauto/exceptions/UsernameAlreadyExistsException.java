package az.code.myauto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "This username already exists, choose other username")
public class UsernameAlreadyExistsException extends RuntimeException {
}
