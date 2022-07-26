package ohmry.workus.user.controller;

import ohmry.workus.core.*;
import ohmry.workus.user.domain.User;
import ohmry.workus.user.model.*;
import ohmry.workus.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        User user = userService.createUser(request.email, request.password, request.name);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(ApiStatus.SUCCESS, UserInfo.valueOf(user)));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(HttpServletRequest servletRequest, @RequestBody SigninRequest request) {
        request.validate();
        User user = userService.getUser(request.email);
        user.verify(request.password);
        userService.storeUserSession(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/signout")
    public ResponseEntity<ApiResponse> signout(HttpServletRequest servletRequest) {
        SessionUtils.expire(servletRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(ApiStatus.SUCCESS));
    }

    @UserSessionRequired
    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser(HttpServletRequest servletRequest) {
        User user = (User) servletRequest.getAttribute(ApiRequestDispatcher.USER_INFO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(ApiStatus.SUCCESS, UserInfo.valueOf(user)));
    }

    @UserSessionRequired
    @PutMapping("/user")
    public ResponseEntity<ApiResponse> updateUser(HttpServletRequest servletRequest, @RequestBody UserUpdateRequest request) {
        request.validate();
        UserInfo userInfo = (UserInfo) servletRequest.getAttribute(ApiRequestDispatcher.USER_INFO);
        userInfo = userService.updateUser(userInfo.id, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(ApiStatus.SUCCESS, userInfo));
    }
}
