package howmuchcar.infra.status

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

enum class CarErrorStatus(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val message: String
) : BaseCodeInterface {

    EXIST_CAR(HttpStatus.BAD_REQUEST, "ROOM-001", "이미 존재하는 채팅방입니다"),
    NOT_EXIST_CAR(HttpStatus.BAD_REQUEST, "CAR-002", "존재하지 않는 차량입니다");

    private val isSuccess: Boolean = false

    override fun getCode(): BaseCodeDto {
        return BaseCodeDto(
            httpStatus = httpStatus,
            isSuccess = isSuccess,
            code = code,
            message = message
        )
    }
}