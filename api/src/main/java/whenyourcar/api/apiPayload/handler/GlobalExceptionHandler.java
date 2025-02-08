package whenyourcar.api.apiPayload.handler;

import whenyourcar.common.code.exception.AuthenticationException;
import whenyourcar.common.code.exception.GeneralException;
import whenyourcar.common.code.status.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import whenyourcar.api.apiPayload.ApiResponse;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
        ErrorStatus errorStatus = ex.getErrorCode(); // ErrorStatus 가져오기
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.onFailure(errorStatus.getCode(),
                        ex.getMessage(),errorStatus.getMessage()));
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(GeneralException ex) {
        ErrorStatus errorStatus = ex.getErrorCode();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure(
                        errorStatus.getCode(),
                        ex.getMessage(),
                        errorStatus.getMessage()));
    }
}