package cn.jieyuc.yingxu.video.identity.domain.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.jieyuc.yingxu.video.identity.domain.service.PasswordHasher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Md5PasswordHasher implements PasswordHasher {
    @Value("${password.salt}")
    private String salt;

    @Override
    public String hash(String password) {
        MD5.create();
        return "";
    }
}
