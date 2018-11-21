package exceptions;

public class JacobiMethodException extends Exception {
    private String message;

    public JacobiMethodException(String message) {
        this.message = "Jacobi Method Exception[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
