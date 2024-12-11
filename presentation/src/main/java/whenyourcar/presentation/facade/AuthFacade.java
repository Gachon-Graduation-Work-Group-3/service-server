package whenyourcar.presentation.facade;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import whenyourcar.domain.auth.dto.TokenInfo;
import whenyourcar.domain.auth.service.AuthService;
import whenyourcar.domain.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final UserService userService;

    public String authSocialToken(String token) {
        return authService.verifySocialToken(token);
    }
    public void authAccessToken(HttpServletRequest request) {
        String email = authService.getEmailToJwtToken(request);
        Authentication auth = userService.findUserByEmail(email);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public TokenInfo generateToken(OAuth2User oAuth2User){
        return authService.generateToken(oAuth2User);
    }
}
