package howmuchcar.infra.status

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

enum class UserErrorStatus(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val message: String
) : BaseCodeInterface {

    NOT_EXIST_USER(HttpStatus.UNAUTHORIZED, "USER-001", "사용자가 존재하지 않습니다");

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