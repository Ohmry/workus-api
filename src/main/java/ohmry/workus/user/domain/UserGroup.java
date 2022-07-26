package ohmry.workus.user.domain;

import ohmry.workus.group.domain.GroupUser;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class UserGroup {
    @OneToMany(mappedBy = "user")
    private List<GroupUser> groups;

    protected UserGroup() {
        this.groups = new ArrayList<>();
    }

    public List<GroupUser> getGroups() {
        return this.groups;
    }
}
