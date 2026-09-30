package cn.jieyuc.yingxu.video.shared.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestResponse<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    private static final int SUCCESS_CODE = 0;
    private static final int FAILED_CODE = 500;

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(SUCCESS_CODE, "success", data,  true);
    }

    public static RestResponse<Void> failed(String message) {
        return new RestResponse<>(FAILED_CODE, message, null, false);
    }
}
