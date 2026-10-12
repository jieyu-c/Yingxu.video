package cn.jieyuc.yingxu.video.identity.infrastructure.auth;

import cn.dev33.satoken.stp.StpUtil;
import cn.jieyuc.yingxu.video.identity.application.port.SessionIssuer;
import cn.jieyuc.yingxu.video.identity.domain.model.UserId;
import org.springframework.stereotype.Component;

@Component
public class SaTokenSessionIssuer implements SessionIssuer {
    @Override
    public String issue(UserId userId) {
        StpUtil.login(userId.value());
        return StpUtil.getTokenValue();
    }
}
