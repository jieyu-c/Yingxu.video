package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.jieyuc.yingxu.video.identity.application.UserApplicationService;
import cn.jieyuc.yingxu.video.identity.application.command.LoginCommand;
import cn.jieyuc.yingxu.video.identity.application.command.RegisterCommand;
import cn.jieyuc.yingxu.video.shared.web.RestResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = UserResponse.from(userApplicationService.register(
                new RegisterCommand(request.phone(), request.password())));
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.success(response));
    }

    @PostMapping("/send-verify-code")
    public void sendVerifyCode() {

    }

    @PostMapping("/login")
    public RestResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return RestResponse.success(LoginResponse.from(userApplicationService.login(
                new LoginCommand(request.phone(), request.password()))));
    }

    @SaCheckLogin
    @GetMapping
    public RestResponse<UserResponse> info() {
        Long userId = StpUtil.getLoginIdAsLong();
        return RestResponse.success(UserResponse.maskedFrom(userApplicationService.findById(userId)));
    }

}
