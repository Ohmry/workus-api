package ohmry.workus.core.exception;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException(Class<?> object) {
        super(object.getName());
    }
}
