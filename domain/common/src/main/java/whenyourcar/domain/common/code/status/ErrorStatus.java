package whenyourcar.domain.common.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import whenyourcar.domain.common.code.BaseErrorCode;
import whenyourcar.domain.common.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    USER_SOCIAL_ACCESS_TOKEN_NOT_VERITY(HttpStatus.UNAUTHORIZED, "USER2010", "wrong social access token"),
    USER_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "USER2011", "user is already existed"),
    USER_ACCESS_TOKEN_NOT_VERITY(HttpStatus.UNAUTHORIZED, "USER2012", "wrong access token"),

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
