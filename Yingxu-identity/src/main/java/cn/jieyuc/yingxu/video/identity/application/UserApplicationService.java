package cn.jieyuc.yingxu.video.identity.application;

import cn.jieyuc.yingxu.video.identity.application.command.LoginCommand;
import cn.jieyuc.yingxu.video.identity.application.command.RegisterCommand;
import cn.jieyuc.yingxu.video.identity.application.result.LoginResult;
import cn.jieyuc.yingxu.video.identity.domain.model.User;

public interface UserApplicationService {
    User register(RegisterCommand command);

    LoginResult login(LoginCommand command);

    User findById(Long userId);
}
