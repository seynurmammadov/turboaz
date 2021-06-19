package az.code.myauto.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("This email already exists, choose other email");
    }
}