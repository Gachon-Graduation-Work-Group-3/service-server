package whenyourcar.domain.common.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import whenyourcar.domain.common.code.BaseCode;
import whenyourcar.domain.common.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    USER_SIGNUP_SUCCESS(HttpStatus.OK, "USER2000", "signup success"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "USER2001", "login success"),
    USER_PROFILE_SUCCESS(HttpStatus.OK, "USER2002", "user profile search success"),
    USER_LIKE_SUCCESS(HttpStatus.OK, "USER2003", "user like success"),

    USER_SEARCH_LIKE(HttpStatus.OK, "USERLIKE2001", "search user like success"),
    USER_DELETE_LIKE(HttpStatus.OK, "USERLIKE2001", "search user like success"),


    CAR_MAIN_PAGE_SUCCESS(HttpStatus.OK, "CAR2001", "car list search success"),
    CAR_DESC_SUCCESS(HttpStatus.OK, "CAR2002", "car desc search success"),
    CAR_DETAIL_SUCCESS(HttpStatus.OK, "CAR2003", "car detail list search success"),
    CAR_POST_SALE_SUCCESS(HttpStatus.OK, "CAR2003", "car sell post success"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
