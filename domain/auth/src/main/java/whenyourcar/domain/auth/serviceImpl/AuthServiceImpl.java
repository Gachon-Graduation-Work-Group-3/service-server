package whenyourcar.domain.auth.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import whenyourcar.domain.auth.dto.SocialResponse;
import whenyourcar.domain.auth.dto.TokenInfo;
import whenyourcar.domain.auth.exception.AuthenticationException;
import whenyourcar.domain.auth.service.AuthService;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.entity.User;

import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private static final String SECRET_KEY = "asdfasdf12341234asdfasdf12341234djoifqnowefnoqwenfqwenofqnwenofnqewfqowenfoqwefnqwnefkdsvcxzmnvmzxcvzv"; // 최소 256비트 이상 필요
    private final Key key;

    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(decodedKey); // SecretKey 생성
    }
    @Override
    public String verifySocialToken(String accessToken) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("oauth2.googleapis.com")
                .path("/tokeninfo")
                .queryParam("access_token", accessToken)
                .build()
                .toUri();
        try {
            return restTemplate.getForObject(uri, SocialResponse.SocialDto.class).getEmail();
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException(ErrorStatus.USER_SOCIAL_ACCESS_TOKEN_NOT_VERITY);
        }
    }

    @Override
    public TokenInfo generateToken(OAuth2User oAuth2User) {
        long tokenPeriod = 1000L * 60L * 30L; // 30분
        long refreshPeriod = 1000L * 60L * 60L * 2L; // 2시간

        Claims claims = Jwts.claims().setSubject(oAuth2User.getAttribute("email"));
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUid(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Date getExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody().getExpiration();
    }

    @Override
    public String getEmailToJwtToken(HttpServletRequest request) {
        String token = getJwtFromHeader(request);
        System.out.println(token);
        if (token != null && verifyToken(token)) {
            return getUid(token);
        }
        else {
            throw new AuthenticationException(ErrorStatus.USER_ACCESS_TOKEN_NOT_VERITY);
        }
    }
}
