package cn.jieyuc.yingxu.video.web.dto;

public class RestResponse<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    public static <T> RestResponse success(T data) {
        RestResponse<T> result = new RestResponse<T>();
        result.data = data;
        result.code = 0;
        result.message = "success";
        return result;
    }
}
