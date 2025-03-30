package howmuchcar.common.exception

import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.common.code.BaseCodeDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice(annotations = [RestController::class])
class ExceptionAdvise : ResponseEntityExceptionHandler(){
    @ExceptionHandler(value = [RestApiException::class])
    fun handleRestApiException(e: RestApiException): ResponseEntity<ApiResponse<String>> {
        val errorCode = e.getErrorCode()
        return handleExceptionInternal(errorCode)
    }

    private fun handleExceptionInternal(errorCode: BaseCodeDto): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .status(errorCode.httpStatus.value())
            .body(ApiResponse.onFailure(errorCode.code, errorCode.message, null))
    }
}