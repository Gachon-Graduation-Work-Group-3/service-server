package whenyourcar.common.code.exception;

import whenyourcar.common.code.BaseErrorCode;
import whenyourcar.common.code.status.ErrorStatus;

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
