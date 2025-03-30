package howmuchcar.common.filter

import com.fasterxml.jackson.databind.ObjectMapper
import howmuchcar.common.exception.RestApiException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Component
@RequiredArgsConstructor
class JwtExceptionFilter(): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: RestApiException) {
            setErrorResponse(response, e)
        }
    }

    private fun setErrorResponse(response: HttpServletResponse, e :RestApiException) {
        response.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            status = HttpServletResponse.SC_UNAUTHORIZED
        }

        val errorDetail = HashMap<String, String>()
        errorDetail["code"] = e.getErrorCode().code
        errorDetail["message"] = e.getErrorCode().message
        errorDetail["timestamp"] = LocalDateTime.now().toString()

        val mapper = ObjectMapper()
        mapper.writeValue(response.outputStream, errorDetail)
    }
}