package cn.jieyuc.yingxu.video.identity.application.impl;

import cn.jieyuc.yingxu.video.identity.application.UserApplicationService;
import cn.jieyuc.yingxu.video.identity.application.command.LoginCommand;
import cn.jieyuc.yingxu.video.identity.application.command.RegisterCommand;
import cn.jieyuc.yingxu.video.identity.application.port.SessionIssuer;
import cn.jieyuc.yingxu.video.identity.application.result.LoginResult;
import cn.jieyuc.yingxu.video.identity.domain.model.Password;
import cn.jieyuc.yingxu.video.identity.domain.model.Phone;
import cn.jieyuc.yingxu.video.identity.domain.model.User;
import cn.jieyuc.yingxu.video.identity.domain.repository.UserRepository;
import cn.jieyuc.yingxu.video.identity.domain.service.PasswordHasher;
import cn.jieyuc.yingxu.video.shared.domain.DomainException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final SessionIssuer sessionIssuer;

    public UserApplicationServiceImpl(UserRepository userRepository,
                                      PasswordHasher passwordHasher,
                                      SessionIssuer sessionIssuer) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.sessionIssuer = sessionIssuer;
    }

    @Override
    @Transactional
    public User register(RegisterCommand command) {
        Phone phone = Phone.of(command.phone());
        if (userRepository.findByPhone(phone).isPresent()) {
            throw DomainException.of("该手机号已注册");
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(Password.of(command.password(), passwordHasher));
        try {
            return userRepository.save(user);
        } catch (DuplicateKeyException exception) {
            throw DomainException.of("该手机号已注册");
        }
    }

    @Override
    public LoginResult login(LoginCommand command) {
        User user = userRepository.findByPhone(Phone.of(command.phone()))
                .orElseThrow(() -> DomainException.of("手机号或密码错误"));
        if (!user.getPassword().matches(command.password(), passwordHasher)) {
            throw DomainException.of("手机号或密码错误");
        }
        return new LoginResult(user.getId().value(), sessionIssuer.issue(user.getId()));
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> DomainException.of("用户不存在"));
    }
}
