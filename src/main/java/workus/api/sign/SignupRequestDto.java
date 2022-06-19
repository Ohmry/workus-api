package workus.api.sign;

import lombok.Data;
import workus.api.user.User;

/**
 * 회원가입 시 사용되는 Dto 클래스
 */
@Data
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;

//    public User toEntity () {
//        return User.builder()
//                .email(email)
//                .name(name)
//                .password(password)
//                .build();
//    }
}
