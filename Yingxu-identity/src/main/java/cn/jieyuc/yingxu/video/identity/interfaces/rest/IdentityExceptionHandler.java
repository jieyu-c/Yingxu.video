package cn.jieyuc.yingxu.video.identity.interfaces.rest;

import cn.jieyuc.yingxu.video.shared.domain.DomainException;
import cn.jieyuc.yingxu.video.shared.web.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IdentityExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<RestResponse<Void>> handleDomainException(DomainException exception) {
        return ResponseEntity.badRequest().body(RestResponse.failed(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Void>> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError() == null
                ? "请求参数不正确"
                : exception.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResponse.failed(message));
    }
}
