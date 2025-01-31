package whenyourcar.domain.user.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import whenyourcar.storage.mysql.data.entity.User;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class SessionUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 859896355423602976L;

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
