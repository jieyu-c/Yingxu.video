package cn.jieyuc.yingxu.video.identity.infrastructure.converter;

import cn.jieyuc.yingxu.video.identity.domain.model.Password;
import cn.jieyuc.yingxu.video.identity.domain.model.Phone;
import cn.jieyuc.yingxu.video.identity.domain.model.User;
import cn.jieyuc.yingxu.video.identity.domain.model.UserId;
import cn.jieyuc.yingxu.video.identity.infrastructure.persistence.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Converts the user aggregate between the domain and persistence representations.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserPersistenceConverter {
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "createdAt", source = "createTime")
    UserDO toDO(User user);

    @Mapping(target = "password", source = "passwordHash")
    @Mapping(target = "createTime", source = "createdAt")
    User toDomain(UserDO userDO);

    default Long toLong(UserId userId) {
        return userId == null ? null : userId.value();
    }

    default UserId toUserId(Long id) {
        return id == null ? null : UserId.of(id);
    }

    default String toString(Phone phone) {
        return phone == null ? null : phone.value();
    }

    default Phone toPhone(String phone) {
        return phone == null ? null : Phone.of(phone);
    }

    default String toString(Password password) {
        return password == null ? null : password.value();
    }

    default Password toPassword(String passwordHash) {
        return passwordHash == null ? null : Password.restore(passwordHash);
    }
}
