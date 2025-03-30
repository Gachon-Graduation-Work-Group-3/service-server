package howmuchcar.common.exception

import howmuchcar.common.code.BaseCodeDto
import howmuchcar.common.code.BaseCodeInterface
import lombok.AllArgsConstructor

@AllArgsConstructor
class RestApiException(
    private val errorCode: BaseCodeInterface
) : RuntimeException() {

    fun getErrorCode() : BaseCodeDto {
        return this.errorCode.getCode()
    }
}