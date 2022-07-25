package ohmry.workus.core;

public enum ApiStatus {
    SUCCESS(0, "정상"),
    /** Common */
    UNAUTHORIZATION(-999, "인증받지 못한 요청입니다."),
    INVALID_PARAMETER(-998, "파라미터가 올바르지 않습니다."),
    /** User */
    EMAIL_IS_ALREADY_EXISTS(-899, "이미 사용 중인 이메일 주소입니다."),
    USER_NOT_FOUND(-898, "사용자 정보를 찾을 수 없습니다."),
    INVALID_USER_CREDENTIAL(-897, "이메일 주소 또는 비밀번호가 올바르지 않습니다.")
    ;
    private final int code;
    private final String message;

    ApiStatus (int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode () { return this.code; }
    public String getMessage () { return this.message; }
}
