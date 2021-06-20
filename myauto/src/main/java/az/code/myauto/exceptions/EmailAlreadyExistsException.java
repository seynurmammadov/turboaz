package az.code.myauto.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException() {
        super("This email already exists, choose other email");
    }
}