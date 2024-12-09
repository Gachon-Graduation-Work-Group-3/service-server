package whenyourcar.domain.user.serviceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import whenyourcar.domain.auth.dto.TokenInfo;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.domain.user.converter.UserConverter;
import whenyourcar.domain.user.exception.GeneralException;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.domain.user.dto.UserRequest;
import whenyourcar.domain.user.dto.UserResponse;
import whenyourcar.storage.mysql.repository.UserRepository;
import whenyourcar.domain.user.service.UserService;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    @Override
    public void signup(UserRequest.SignUpDto signUpDto, String email)  {
        if(userRepository.existsByEmail(email)) {
            throw new GeneralException(ErrorStatus.USER_ALREADY_EXIST);
        }
        User user = userConverter.toUser(signUpDto, email);
        userRepository.save(user);

        return;
    }

    @Override
    public Authentication findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
        return getAuthentication(user);
    }

    @Override
    public String getEmail() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getEmail();
    }

    public Authentication getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
