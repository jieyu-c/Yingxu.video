package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "手机号不能为空") String phone,
        @NotBlank(message = "密码不能为空")
        @Size(min = 8, max = 16, message = "密码长度必须在 8 到 16 个字符之间")
        String password
) {
}
