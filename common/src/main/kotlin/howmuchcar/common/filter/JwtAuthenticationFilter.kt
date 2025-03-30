package howmuchcar.common.filter

import howmuchcar.common.auth.JwtProvider
import howmuchcar.common.code.status.AuthErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.repository.user.UserCommonRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.apache.catalina.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userCommonRepository: UserCommonRepository

): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = jwtProvider.resolveToken(request)

        if(accessToken !=null && jwtProvider.validateAccessToken(accessToken)) {
            val authentication = getAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        } else {

        }
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(accessToken: String): Authentication {
        val email : String = jwtProvider.extractAccessTokenToEmail(accessToken)
        val user = userCommonRepository.findByEmail(email)
            .orElseThrow{ RestApiException(AuthErrorStatus.INVALID_ACCESS_TOKEN)}

        return UsernamePasswordAuthenticationToken(user, null, listOf())
    }
}