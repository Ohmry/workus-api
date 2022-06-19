package workus.api.sign;

import lombok.Data;

/**
 * 사용자 인증을 위해 사용되는 Dto 클래스
 */
@Data
public class SinginRequestDto {
    private String email;
    private String password;
}
