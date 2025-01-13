package whenyourcar.presentation.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException {

        String targetUrl = UriComponentsBuilder.newInstance()
                .scheme("http") // HTTPS라면 "https"로 변경
                .host("localhost") // 배포 환경에서는 클라이언트 도메인으로 변경
                .port(3000) // React의 포트
                .path("/login-success") // React 페이지의 경로
                .build()
                .toUriString();

        System.out.println(targetUrl);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
