package cn.jieyuc.yingxu.video.shared.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DomainException extends RuntimeException {
    private String msg;

    private static DomainException of(String msg) {
        return new DomainException(msg);
    }
}
