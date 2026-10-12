package cn.jieyuc.yingxu.video.identity.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class User {
    private UserId id;
    private Phone phone;
    private Password password;
    private LocalDateTime createTime;
}
