package cn.jieyuc.yingxu.video.identity.infrastructure.persistence;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.jieyuc.yingxu.video.identity.domain.model.Phone;
import cn.jieyuc.yingxu.video.identity.domain.model.User;
import cn.jieyuc.yingxu.video.identity.domain.repository.UserRepository;
import cn.jieyuc.yingxu.video.identity.infrastructure.converter.UserPersistenceConverter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final UserPersistenceConverter userPersistenceConverter;

    public UserRepositoryImpl(UserMapper userMapper, UserPersistenceConverter userPersistenceConverter) {
        this.userMapper = userMapper;
        this.userPersistenceConverter = userPersistenceConverter;
    }

    @Override
    public User save(User user) {
        UserDO userDO = userPersistenceConverter.toDO(user);
        userMapper.insert(userDO);
        return userPersistenceConverter.toDomain(userDO);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMapper.selectById(id)).map(userPersistenceConverter::toDomain);
    }

    @Override
    public Optional<User> findByPhone(Phone phone) {
        UserDO userDO = userMapper.selectOne(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getPhone, phone.value()));
        return Optional.ofNullable(userDO).map(userPersistenceConverter::toDomain);
    }
}
