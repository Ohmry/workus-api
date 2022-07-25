package ohmry.workus.user.controller;

import ohmry.workus.core.ApiResponse;
import ohmry.workus.core.ApiStatus;
import ohmry.workus.core.SessionKeys;
import ohmry.workus.core.SessionUtils;
import ohmry.workus.user.exception.UserNotFoundException;
import ohmry.workus.user.model.SigninRequest;
import ohmry.workus.user.model.SignupRequest;
import ohmry.workus.user.model.UserInfoWithCredential;
import ohmry.workus.user.model.UserInfo;
import ohmry.workus.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(HttpServletRequest servletRequest, @RequestBody SignupRequest request) {
        request.validate();
        UserInfo userInfo = userService.createUser(request.email, request.password, request.name);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(ApiStatus.SUCCESS, userInfo));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(HttpServletRequest servletRequest, @RequestBody SigninRequest request) {
        request.validate();
        UserInfoWithCredential userInfoWithCredential = userService.getUserWithCredentialByEmail(request.email);
        userService.verify(userInfoWithCredential, request.password);
        UserInfo userInfo = UserInfo.parse(userInfoWithCredential);
        SessionUtils.set(servletRequest, SessionKeys.USER_INFO, userInfo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(ApiStatus.SUCCESS, userInfo));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(HttpServletRequest servletRequest) {
        UserInfo userInfo = (UserInfo) SessionUtils.get(servletRequest, SessionKeys.USER_INFO);
        if (userInfo == null) {
            throw new UserNotFoundException();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(ApiStatus.SUCCESS, userInfo));
    }
}
