package ohmry.workus.user.domain;

import ohmry.workus.core.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TB_CO_USER")
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
}
