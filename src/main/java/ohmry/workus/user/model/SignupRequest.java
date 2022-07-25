package ohmry.workus.user.model;

import ohmry.workus.core.RequestObject;
import ohmry.workus.core.exception.IllegalRequestException;
import org.springframework.util.StringUtils;

public class SignupRequest implements RequestObject {
    public String email;
    public String password;
    public String name;

    @Override
    public void validate() {
        if (!StringUtils.hasText(email) || !StringUtils.hasText(password) || !StringUtils.hasText(name)) {
            throw new IllegalRequestException(this.getClass());
        }
    }
}
