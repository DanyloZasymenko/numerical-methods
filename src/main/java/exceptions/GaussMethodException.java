package exceptions;

public class GaussMethodException extends Exception {

    private String message;

    public GaussMethodException(String message) {
        this.message = "Gauss Method Exception:[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
