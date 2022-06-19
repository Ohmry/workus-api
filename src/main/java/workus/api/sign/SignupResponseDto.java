package workus.api.sign;

import lombok.Builder;
import lombok.Data;
import workus.api.user.User;
import workus.api.user.UserRole;

/**
 * 회원가입 후 반환할 데이터를 담는 Dto
 */
@Builder
@Data
public class SignupResponseDto {
    private String email;
    private String name;
    private UserRole role;

//    public SignupResponseDto parse (User entity) {
//        return SignupResponseDto.builder()
//                .email(entity.getEmail())
//                .name(entity.getName())
//                .role(entity.getRole())
//                .build();
//    }
}
