package ohmry.workus.user.model;

import ohmry.workus.group.domain.GroupUser;
import ohmry.workus.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfoWithGroup extends UserInfo {
    public final List<GroupUser> groups;

    protected UserInfoWithGroup(Long id, String email, String name, List<GroupUser> groups) {
        super(id, email, name);
        this.groups = groups == null ? new ArrayList<>() : groups;
    }

    public static UserInfoWithGroup valueOf(User user) {
        return new UserInfoWithGroup(user.getId(), user.getEmail(), user.getName(), user.getGroups());
    }
}
