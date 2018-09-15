package pl.dashboard.nbp.exceptions;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
}
