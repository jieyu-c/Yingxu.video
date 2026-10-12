package cn.jieyuc.yingxu.video.shared.domain;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public static DomainException of(String message) {
        return new DomainException(message);
    }
}
