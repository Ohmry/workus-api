package ohmry.workus.user.domain;

import ohmry.workus.core.BaseEntity;
import ohmry.workus.core.PasswordUtils;
import ohmry.workus.group.domain.GroupUser;
import ohmry.workus.user.exception.InvalidUserCredentialException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Embedded
    private UserCredential credential;
    @Embedded
    private UserGroup groups;

    protected User() {}
    public User(String email, String password, String name) {
        this.credential = new UserCredential(email, password);
        this.name = name;
        this.groups = new UserGroup();
    }

    public Long getId() {
        return this.id;
    }
    public String getEmail() {
        return this.credential.getEmail();
    }
    public String getPassword() {
        return this.credential.getPassword();
    }
    public String getName() {
        return this.name;
    }
    public List<GroupUser> getGroups() {
        return this.groups.getGroups();
    }

    public void updateName(String name) {
        this.name = name;
    }
    public boolean verify(String password) {
        return this.credential.verify(password);
    }
}
