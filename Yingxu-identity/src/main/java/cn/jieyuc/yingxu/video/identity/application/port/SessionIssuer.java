package cn.jieyuc.yingxu.video.identity.application.port;

import cn.jieyuc.yingxu.video.identity.domain.model.UserId;

public interface SessionIssuer {
    String issue(UserId userId);
}
