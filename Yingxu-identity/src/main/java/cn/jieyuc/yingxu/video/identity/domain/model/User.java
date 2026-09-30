package cn.jieyuc.yingxu.video.identity.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class User {
    private UserId id;
    private Phone phone;
    private Password password;
    private LocalTime createTime;

    public static void main(String[] args) {
        var birthday = LocalDateTime.of(2001, 3, 15, 10, 10);

        System.out.println(birthday);
    }
}
