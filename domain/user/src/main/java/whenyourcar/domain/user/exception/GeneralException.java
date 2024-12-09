package whenyourcar.domain.user.exception;


import whenyourcar.domain.common.code.BaseErrorCode;
import whenyourcar.domain.common.code.status.ErrorStatus;

public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        super(code.getReason().getMessage());
        this.code = code;
    }

    public ErrorStatus getErrorCode() {
        return (ErrorStatus)code;
    }
}
