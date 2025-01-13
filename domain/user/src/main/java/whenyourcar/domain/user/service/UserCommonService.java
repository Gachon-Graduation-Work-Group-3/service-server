package whenyourcar.domain.user.service;

import jakarta.servlet.http.HttpSession;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.storage.mysql.data.entity.User;

public interface UserCommonService {
    public SessionUser getSessionUser(HttpSession httpSession);
    public User getUser(HttpSession httpSession);
}
