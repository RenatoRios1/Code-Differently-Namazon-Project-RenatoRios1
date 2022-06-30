package exceptions;

public class AccountCreationException extends  Exception{

    public AccountCreationException() {
        super("The account could not be created.");
    }
}
