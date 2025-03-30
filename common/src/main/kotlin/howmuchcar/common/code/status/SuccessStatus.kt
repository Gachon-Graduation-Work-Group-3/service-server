package howmuchcar.common.code.status

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

enum class SuccessStatus(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val message: String
) : BaseCodeInterface {

    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다.");

    private val isSuccess: Boolean = true

    override fun getCode(): BaseCodeDto {
        return BaseCodeDto(
            httpStatus = httpStatus,
            isSuccess = isSuccess,
            code = code,
            message = message
        )
    }
}