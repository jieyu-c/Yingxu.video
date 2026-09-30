package cn.jieyuc.yingxu.video.identity.domain.model;

import cn.jieyuc.yingxu.video.VO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserId implements VO<Long> {
    private Long id;

    public static UserId of(Long userId) {
        return new UserId(userId);
    }

    @Override
    public Long value() {
        return this.id;
    }
}
