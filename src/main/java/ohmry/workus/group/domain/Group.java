package ohmry.workus.group.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ohmry.workus.core.BaseEntity;
import ohmry.workus.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "TB_GROUP")
@Entity
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Column(length = 200)
    private String description;
    @OneToOne
    @JoinColumn(name = "owner")
    private User owner;
    @OneToMany(mappedBy = "group")
    private List<GroupUser> users;

    protected Group() {}
    public Group(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public User getOwner() {
        return this.owner;
    }
    public List<GroupUser> getUsers() {
        return this.users;
    }
}
