package ohmry.workus.user.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("이미 사용 중인 이메일 주소 입니다, " + email);
    }
}
