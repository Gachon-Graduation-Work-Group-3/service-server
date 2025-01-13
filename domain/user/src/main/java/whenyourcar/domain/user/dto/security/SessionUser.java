package whenyourcar.domain.user.dto.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import whenyourcar.storage.mysql.data.entity.User;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class SessionUser implements Serializable {
    private Long userId;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
