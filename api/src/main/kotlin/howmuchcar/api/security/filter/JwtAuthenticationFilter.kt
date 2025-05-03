package howmuchcar.api.security.filter

import howmuchcar.application.port.out.db.user.UserPort
import howmuchcar.common.auth.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userPort: UserPort

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
        val user = userPort.findByEmail(email)
        return UsernamePasswordAuthenticationToken(user, null, listOf())
    }
}