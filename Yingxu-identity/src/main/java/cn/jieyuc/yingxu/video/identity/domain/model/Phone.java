package cn.jieyuc.yingxu.video.identity.domain.model;

import cn.jieyuc.yingxu.video.VO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Phone implements VO<String> {
    private String phone;

    private static final String PHONE_REGEX = "^1[3|4|5|6|7|8|9][0-9]{9}$";

    public static Phone of(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw
        }
        return new Phone(phone);
    }

    @Override
    public String value() {
        return this.phone;
    }
}
