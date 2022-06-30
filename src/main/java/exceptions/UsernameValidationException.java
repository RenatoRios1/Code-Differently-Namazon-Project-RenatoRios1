package exceptions;

public class UsernameValidationException extends Exception{

    public UsernameValidationException(String mssg){
        super(mssg);
    }
}
