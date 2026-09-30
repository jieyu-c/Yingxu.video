package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/register")
    public void register(@RequestBody LoginRequest request) {

    }

    @PostMapping("/send-verify-code")
    public void sendVerifyCode() {

    }

    @PostMapping("/login")
    public void login() {

    }

    @SaCheckLogin
    @GetMapping
    public void info() {
        String userId = StpUtil.getLoginId().toString();
    }

}
