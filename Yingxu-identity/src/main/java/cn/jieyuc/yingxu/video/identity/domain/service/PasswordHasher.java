package cn.jieyuc.yingxu.video.identity.domain.service;


public interface PasswordHasher {
    String hash(String password);

    boolean matches(String rawPassword, String passwordHash);
}
