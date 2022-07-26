package ohmry.workus.group.controller;

import ohmry.workus.core.ApiRequestDispatcher;
import ohmry.workus.core.ApiResponse;
import ohmry.workus.core.ApiStatus;
import ohmry.workus.core.UserSessionRequired;
import ohmry.workus.group.model.GroupCreateRequest;
import ohmry.workus.group.model.GroupInfo;
import ohmry.workus.group.service.GroupService;
import ohmry.workus.user.domain.User;
import ohmry.workus.user.model.UserInfo;
import ohmry.workus.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    public GroupController(GroupService groupService,
                           UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @UserSessionRequired
    @PostMapping
    public ResponseEntity<ApiResponse> createGroup(HttpServletRequest servletRequest, @RequestBody GroupCreateRequest request) {
        request.validate();
        UserInfo userInfo = (UserInfo) servletRequest.getAttribute(ApiRequestDispatcher.USER_INFO);
        User user = userService.getUser(userInfo.id);
        GroupInfo groupInfo = groupService.createGroup(user, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(ApiStatus.SUCCESS, groupInfo));
    }
}
