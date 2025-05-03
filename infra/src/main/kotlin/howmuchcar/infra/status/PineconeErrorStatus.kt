package howmuchcar.infra.status

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

enum class PineconeErrorStatus(
    private val httpStatus: HttpStatus,
    private val code: String,
    private val message: String
) : BaseCodeInterface {

    ERROR_SAVING_PINECONE(HttpStatus.BAD_REQUEST, "PINECONE-001", "벡터 저장중 오류 발생"),
    ERROR_LOADING_PINECONE(HttpStatus.BAD_REQUEST, "PINECONE-002", "벡터 쿼리중 오류 발생");

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