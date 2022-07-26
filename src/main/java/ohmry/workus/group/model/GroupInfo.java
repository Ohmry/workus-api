package ohmry.workus.group.model;

import ohmry.workus.group.domain.Group;
import ohmry.workus.user.model.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

public class GroupInfo {
    public final Long id;
    public final String name;
    public final String description;
    public final UserInfo owner;
    public final List<UserInfo> users;

    protected GroupInfo(Long id, String name, String description, UserInfo owner, List<UserInfo> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.users = users;
    }

    public static GroupInfo valueOf(Group group) {
        List<UserInfo> users = group.getUsers().stream()
                .map(groupUser -> UserInfo.valueOf(groupUser.getUser())).collect(Collectors.toList());
        UserInfo owner = UserInfo.valueOf(group.getOwner());
        return new GroupInfo(group.getId(), group.getName(), group.getDescription(), owner, users);
    }
}
