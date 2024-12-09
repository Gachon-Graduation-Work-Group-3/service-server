package whenyourcar.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import whenyourcar.domain.auth.dto.TokenInfo;

import java.util.Date;

public interface AuthService {
    String verifySocialToken(String accessToken);
    TokenInfo generateToken(String email);
    String getJwtFromHeader(HttpServletRequest request);
    boolean verifyToken(String token);
    String getUid(String token);
    String getEmailToJwtToken(HttpServletRequest request);
    Date getExpiration(String token);
}
