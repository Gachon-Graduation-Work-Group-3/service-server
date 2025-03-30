package howmuchcar.common.code

import org.springframework.http.HttpStatus

data class BaseCodeDto (
    val httpStatus: HttpStatus,
    val isSuccess: Boolean,
    val code: String,
    val message: String
)