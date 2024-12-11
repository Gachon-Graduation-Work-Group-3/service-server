package whenyourcar.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import whenyourcar.domain.auth.dto.TokenInfo;

import java.util.Date;

public interface AuthService {
    String verifySocialToken(String accessToken);
    TokenInfo generateToken(OAuth2User oAuth2User);
    String getJwtFromHeader(HttpServletRequest request);
    boolean verifyToken(String token);
    String getUid(String token);
    String getEmailToJwtToken(HttpServletRequest request);
    Date getExpiration(String token);
}
