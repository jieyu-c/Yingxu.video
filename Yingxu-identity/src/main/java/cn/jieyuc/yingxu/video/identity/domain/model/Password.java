package cn.jieyuc.yingxu.video.identity.domain.model;

import cn.jieyuc.yingxu.video.VO;
import cn.jieyuc.yingxu.video.identity.domain.service.PasswordHasher;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Password implements VO<String> {
    private String password;

    /**
     * 根据用户提交的明文密码创建密码值对象，仅保存生成后的哈希值。
     *
     * @param rawPassword 用户提交的明文密码
     * @param passwordHasher 用于生成密码哈希的领域服务
     * @return 包含密码哈希值的密码对象
     */
    public static Password of(String rawPassword, PasswordHasher passwordHasher) {
        return new Password(passwordHasher.hash(rawPassword));
    }

    /**
     * 根据持久化层读取到的既有哈希值恢复密码对象。
     * 此方法不得再次对哈希值加密。
     *
     * @param passwordHash 从持久化存储读取的密码哈希值
     * @return 包含原始哈希值的密码对象
     */
    public static Password restore(String passwordHash) {
        return new Password(passwordHash);
    }

    /**
     * 校验用户提交的明文密码是否与当前保存的哈希值匹配。
     *
     * @param rawPassword 用户提交的明文密码
     * @param passwordHasher 用于校验密码的领域服务
     * @return 明文密码与已保存哈希值匹配时返回 {@code true}
     */
    public boolean matches(String rawPassword, PasswordHasher passwordHasher) {
        return passwordHasher.matches(rawPassword, password);
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
        if (o instanceof Password passwordObj) {
            return this.password.equals(passwordObj.value());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
