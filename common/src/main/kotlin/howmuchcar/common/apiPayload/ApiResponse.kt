package howmuchcar.common.apiPayload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import howmuchcar.common.code.status.SuccessStatus
import java.time.LocalDateTime

@JsonPropertyOrder("timestamp", "code", "message", "result")
data class ApiResponse<T> (
    @JsonProperty("timestamp")
    val timestamp:LocalDateTime = LocalDateTime.now(),
    val code:String,
    val message:String,

    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val result:T? = null
) {
    companion object {
        fun <T> onSuccess(result: T?): ApiResponse<T> {
            return ApiResponse(
                code = SuccessStatus._OK.getCode().code,
                message = SuccessStatus._OK.getCode().message,
                result = result
            )
        }

        fun <T> onFailure(
            code: String,
            message: String,
            data: T?
        ): ApiResponse<T> {
            return ApiResponse(
                code = code,
                message = message,
                result = data
            )
        }
    }
}