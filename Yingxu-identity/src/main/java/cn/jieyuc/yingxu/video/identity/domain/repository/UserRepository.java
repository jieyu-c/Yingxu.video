package cn.jieyuc.yingxu.video.identity.domain.repository;

import cn.jieyuc.yingxu.video.identity.domain.model.Phone;
import cn.jieyuc.yingxu.video.identity.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByPhone(Phone phone);
}
