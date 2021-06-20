package az.code.myauto.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() {
        super("This username already exists, choose other username");
    }
}
