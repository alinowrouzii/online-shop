package exception;

public class HasNotLoggedInException extends Exception{
    private String message;
    public HasNotLoggedInException(String message) {
        super(message);
        this.message = message;
    }
    public String toString() {
        return "UserNotAllowed! :" + message ;
    }
}
