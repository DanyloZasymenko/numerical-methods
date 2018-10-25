package exceptions;

public class JacobisMethodException extends Exception {
    private String message;

    public JacobisMethodException(String message) {
        this.message = "Jacobi`s Method Exception[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
