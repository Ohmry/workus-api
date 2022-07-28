package ohmry.workus.core.exception;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException() {
        super("요청 파라미터의 형식이 올바르지 않습니다. RequestBody로 전달되는 객체의 경우 반드시 RequestObject 인터페이스를 상속받아야 합니다.");
    }
}
