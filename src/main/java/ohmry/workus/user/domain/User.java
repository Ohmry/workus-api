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
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String name;
    @OneToMany(mappedBy = "user")
    private List<GroupUser> groups;

    protected User() {}
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getName() {
        return this.name;
    }
    public List<GroupUser> getGroups() {
        return this.groups;
    }

    public void updateName(String name) {
        this.name = name;
    }
    public void verify(String password) {
        if (!PasswordUtils.verify(this.password, password)) {
            throw new InvalidUserCredentialException();
        }
    }
}
