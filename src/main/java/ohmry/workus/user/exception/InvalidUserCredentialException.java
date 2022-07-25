package ohmry.workus.user.exception;

public class InvalidUserCredentialException extends RuntimeException {
    public InvalidUserCredentialException() {
        super("이메일 주소 또는 비밀번호가 올바르지 않습니다.");
    }
}
