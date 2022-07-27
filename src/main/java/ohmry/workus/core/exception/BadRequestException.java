package ohmry.workus.core.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(Class<?> object) {
        super(object.getName());
    }
}
