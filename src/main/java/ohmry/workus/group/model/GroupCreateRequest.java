package ohmry.workus.group.model;

import ohmry.workus.core.RequestObject;
import ohmry.workus.core.exception.IllegalRequestException;
import org.springframework.util.StringUtils;

public class GroupCreateRequest implements RequestObject {
    public String name;
    public String description;
    @Override
    public void validate() {
        if (!StringUtils.hasText(name)) {
            throw new IllegalRequestException(this.getClass());
        }
    }
}
