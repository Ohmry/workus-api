package ohmry.workus.user.model;

import ohmry.workus.user.domain.User;

public class UserInfoWithCredential extends UserInfo {
    public final String password;

    protected UserInfoWithCredential(Long id, String email, String password, String name) {
        super(id, email, name);
        this.password = password;
    }

    public static UserInfoWithCredential valueOf(User user) {
        return new UserInfoWithCredential(user.getId(), user.getEmail(), user.getPassword(), user.getName());
    }
}
