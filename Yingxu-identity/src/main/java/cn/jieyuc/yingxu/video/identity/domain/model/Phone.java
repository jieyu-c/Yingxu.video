package cn.jieyuc.yingxu.video.identity.domain.model;

import cn.jieyuc.yingxu.video.VO;
import cn.jieyuc.yingxu.video.shared.domain.DomainException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Phone implements VO<String> {
    private String phone;

    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    public static Phone of(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw DomainException.of("手机号格式不正确");
        }
        return new Phone(phone);
    }

    @Override
    public String value() {
        return this.phone;
    }
}
