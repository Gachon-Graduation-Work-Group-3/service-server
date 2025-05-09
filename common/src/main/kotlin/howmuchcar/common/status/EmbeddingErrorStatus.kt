package howmuchcar.infra.status

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

enum class EmbeddingErrorStatus(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val message: String
) : BaseCodeInterface {

    ERROR_EMBEDDING(HttpStatus.BAD_REQUEST, "EMB-001", "임베딩 중 오류 발생");

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