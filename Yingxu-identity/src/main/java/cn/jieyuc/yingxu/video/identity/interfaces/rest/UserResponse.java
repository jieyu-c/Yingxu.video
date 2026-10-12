package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import cn.jieyuc.yingxu.video.identity.domain.model.User;

import java.time.LocalDateTime;

public record UserResponse(Long id, String phone, LocalDateTime createdAt) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId().value(), user.getPhone().value(), user.getCreateTime());
    }

    public static UserResponse maskedFrom(User user) {
        return new UserResponse(user.getId().value(), maskPhone(user.getPhone().value()), user.getCreateTime());
    }

    private static String maskPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}
