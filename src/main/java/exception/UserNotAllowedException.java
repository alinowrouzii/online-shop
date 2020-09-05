package exception;

public class UserNotAllowedException extends Exception {
    private String message;
    public UserNotAllowedException(String message) {
        super(message);
        this.message = message;
    }
    public String toString() {
        return "UserNotAllowed! :" + message ;
    }
}
