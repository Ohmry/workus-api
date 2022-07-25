package ohmry.workus.user.model;

import ohmry.workus.user.domain.User;

public class UserInfo {
    public final Long id;
    public final String email;
    public final String name;

    protected UserInfo(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserInfo valueOf(User user) {
        return new UserInfo(user.getId(), user.getEmail(), user.getName());
    }

    public static UserInfo parse(UserInfoWithCredential userInfoWithCredential) {
        return new UserInfo(userInfoWithCredential.id, userInfoWithCredential.email, userInfoWithCredential.name);
    }
}
