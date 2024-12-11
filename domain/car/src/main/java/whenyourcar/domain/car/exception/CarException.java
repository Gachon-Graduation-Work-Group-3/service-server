package whenyourcar.domain.car.exception;


import whenyourcar.domain.common.code.BaseErrorCode;
import whenyourcar.domain.common.code.status.ErrorStatus;

public class CarException extends RuntimeException{
    private BaseErrorCode code;

    public CarException(BaseErrorCode code) {
        super(code.getReason().getMessage());
        this.code = code;
    }

    public ErrorStatus getErrorCode() {
        return (ErrorStatus)code;
    }
}
