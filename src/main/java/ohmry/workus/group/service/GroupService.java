package ohmry.workus.group.service;

import ohmry.workus.group.domain.Group;
import ohmry.workus.group.model.GroupCreateRequest;
import ohmry.workus.group.model.GroupInfo;
import ohmry.workus.group.repository.GroupRepository;
import ohmry.workus.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public GroupInfo createGroup(User user, GroupCreateRequest request) {
        Group group = new Group(request.name, request.description, user);
        groupRepository.save(group);
        return GroupInfo.valueOf(group);
    }
}
