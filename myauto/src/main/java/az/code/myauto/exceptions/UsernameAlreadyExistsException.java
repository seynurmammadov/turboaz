package az.code.myauto.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("This username already exists, choose other username");
    }
}
