package ohmry.workus.core.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(Class<?> object) {
        super(object.getName() + " 객체의 값이 올바르지 않습니다.");
    }
}
