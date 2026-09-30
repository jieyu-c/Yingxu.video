package cn.jieyuc.yingxu.video.identity.domain.model;

import cn.jieyuc.yingxu.video.VO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Password implements VO<String> {
    private String password;

    public static Password of(String password) {
        // todo 密码需要加密
        return new Password(password);
    }

    @Override
    public String value() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        if (o instanceof String passwordStr) {
            // todo passwordStr加密
            var passwordMd5 = passwordStr;
            return this.password.equals(passwordMd5);
        } else if (o instanceof Password passwordObj) {
            return this.password.equals(passwordObj.value());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
