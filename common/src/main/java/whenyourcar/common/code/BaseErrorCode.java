package whenyourcar.common.code;

import whenyourcar.common.code.dto.ReasonDTO;

public interface BaseErrorCode {
    public ReasonDTO getReason();
    public ReasonDTO getReasonHttpStatus();
}
