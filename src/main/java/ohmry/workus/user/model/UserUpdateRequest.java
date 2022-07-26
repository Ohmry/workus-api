package ohmry.workus.user.model;

import ohmry.workus.core.RequestObject;
import ohmry.workus.core.exception.IllegalRequestException;
import org.springframework.util.StringUtils;

public class UserUpdateRequest implements RequestObject {
    public String name;

    @Override
    public void validate() {
        if (!StringUtils.hasText(name)) {
            throw new IllegalRequestException(this.getClass());
        }
    }
}
