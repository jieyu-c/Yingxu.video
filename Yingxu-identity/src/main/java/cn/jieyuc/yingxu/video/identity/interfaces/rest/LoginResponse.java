package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import cn.jieyuc.yingxu.video.identity.application.result.LoginResult;

public record LoginResponse(Long userId, String token) {
    public static LoginResponse from(LoginResult result) {
        return new LoginResponse(result.userId(), result.token());
    }
}
