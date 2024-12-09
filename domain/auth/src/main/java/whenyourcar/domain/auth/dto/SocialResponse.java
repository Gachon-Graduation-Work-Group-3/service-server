package whenyourcar.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class SocialResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class SocialDto {
        private String azp;
        private String aud;
        private String sub;
        private String scope;
        private String exp;
        private String expires_in;
        private String email;
        private String email_verified;
    }
}
