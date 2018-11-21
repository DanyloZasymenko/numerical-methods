package exceptions;

public class NewtonMethodException extends Exception {

    private String message;

    public NewtonMethodException(String message) {
        this.message = "Newton Method Exception:[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
