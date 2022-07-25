package ohmry.workus.user.model;

import ohmry.workus.core.RequestObject;
import ohmry.workus.core.exception.IllegalRequestException;
import org.springframework.util.StringUtils;

public class SigninRequest implements RequestObject {
    public String email;
    public String password;

    @Override
    public void validate() {
        if (!StringUtils.hasText(email) || !StringUtils.hasText(password)) {
            throw new IllegalRequestException(this.getClass());
        }
    }
}
