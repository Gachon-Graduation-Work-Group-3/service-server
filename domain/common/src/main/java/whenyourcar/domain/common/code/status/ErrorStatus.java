package whenyourcar.domain.common.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import whenyourcar.domain.common.code.BaseErrorCode;
import whenyourcar.domain.common.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    USER_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "USER2011", "user is already existed"),
    USER_IS_NOT_EXIST(HttpStatus.UNAUTHORIZED, "USER2012", "user is not exist"),

    CAR_IS_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "CAR2010", "car is not exist"),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
