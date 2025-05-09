package howmuchcar.common.auth

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

@Component
@RequiredArgsConstructor
class JwtProvider (
    private val redisTemplate: RedisTemplate<String, Any>

){
    @Value("\${jwt.access.secret}")
    private val JWT_ACCESS_SECRET: String? = null

    @Value("\${jwt.refresh.secret}")
    private val JWT_REFRESH_SECRET: String? = null

    @Value("\${jwt.access.expire}")
    private val JWT_ACCESS_EXPIRE: Long? = null

    @Value("\${jwt.refresh.expire}")
    private val JWT_REFRESH_EXPIRE: Long? = null

    @Value("\${jwt.refresh.prefix}")
    private val REFRESH_TOKEN_PREFIX: String? = null

    fun generateAccessToken(userId: String): String {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + JWT_ACCESS_EXPIRE!!))
            .signWith(SignatureAlgorithm.HS256, JWT_ACCESS_SECRET)
            .compact()
    }

    fun generateRefreshToken(userId: String): String {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + JWT_REFRESH_EXPIRE!!)) // 7 days expiration for Refresh Token
            .signWith(SignatureAlgorithm.HS256, JWT_REFRESH_SECRET)
            .compact()
    }


    fun validateAccessToken(token: String?): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_ACCESS_SECRET).build().parseClaimsJws(token)
            return true
        } catch (e: JwtException) {
            return false // 유효하지 않은 토큰 처리
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    fun validateRefreshToken(token: String, email: String): Boolean {
        val key = REFRESH_TOKEN_PREFIX + email
        val storedToken = redisTemplate.opsForHash<Any, Any>()[key, "refreshToken"]

        return storedToken != null && storedToken == token
    }


    fun extractAccessTokenToEmail(accessToken: String?): String {
        return Jwts.parserBuilder()
            .setSigningKey(JWT_ACCESS_SECRET)
            .build()
            .parseClaimsJws(accessToken)
            .body
            .subject
    }

    fun extractRefreshTokenToUserId(refreshToken: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(JWT_REFRESH_SECRET)
            .build()
            .parseClaimsJws(refreshToken)
            .body
            .subject
    }

    fun saveRefreshToken(email: String, refreshToken: String) {
        val key = REFRESH_TOKEN_PREFIX + email
        val tokenData: MutableMap<String, Any> = HashMap()
        tokenData["refreshToken"] = refreshToken
        tokenData["expire"] = JWT_REFRESH_EXPIRE!!

        redisTemplate.opsForHash<Any, Any>().putAll(key, tokenData)
        redisTemplate.expire(key, JWT_REFRESH_EXPIRE!!, TimeUnit.MILLISECONDS)
    }


    private fun isAccessTokenExpired(accessToken: String?): Boolean {
        return extractAccessTokenExpiration(accessToken).before(Date())
    }

    // Extract expiration date from JWT token
    private fun extractAccessTokenExpiration(accessToken: String?): Date {
        return Jwts.parserBuilder()
            .setSigningKey(accessToken)
            .build()
            .parseClaimsJws(accessToken)
            .body
            .expiration
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7)
        }
        return null
    }

    fun resolveToken(header: String?): String? {
        if (StringUtils.hasText(header) && header!!.startsWith("Bearer ")) {
            return header.substring(7)
        }
        return null
    }

}