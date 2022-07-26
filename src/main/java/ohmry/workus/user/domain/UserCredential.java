package ohmry.workus.user.domain;

import ohmry.workus.core.PasswordUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCredential {
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    protected UserCredential(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }

    public boolean verify(String password) {
        return PasswordUtils.verify(this.password, password);
    }
}
