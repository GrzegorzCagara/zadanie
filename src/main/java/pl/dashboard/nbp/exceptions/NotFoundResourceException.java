package pl.dashboard.nbp.exceptions;

public class NotFoundResourceException extends RuntimeException {

    public NotFoundResourceException(String message) {
        super(message);
    }
}
