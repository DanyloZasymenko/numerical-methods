package exceptions;

public class GaussMethodException extends Exception {

    private String message;

    public GaussMethodException(String message) {
        this.message = "GaussMethodException:[ " + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
