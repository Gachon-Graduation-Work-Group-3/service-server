package whenyourcar.domain.user.service;

import org.springframework.security.core.Authentication;
import whenyourcar.domain.user.dto.UserRequest;
import whenyourcar.domain.user.dto.UserResponse;

public interface UserService {
    void signup(UserRequest.SignUpDto signUpDto, String email);
    Authentication findUserByEmail(String email);
    String getEmail();
}
