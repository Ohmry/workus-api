package workus.api.sign;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 사용자 인증 후 결과값으로 반환할 때, 사용되는 Dto 클래스
 */
@Data
public class SinginResponseDto {
    private String email;
    private LocalDateTime expireTime;
}
