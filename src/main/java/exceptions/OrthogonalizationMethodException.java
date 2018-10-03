package exceptions;

public class OrthogonalizationMethodException extends Exception {

    private String message;

    public OrthogonalizationMethodException(String message) {
        this.message = "Orthogonalization Method Exception[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
