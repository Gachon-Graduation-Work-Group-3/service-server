package whenyourcar.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import whenyourcar.domain.user.dto.security.SessionUser;
import whenyourcar.storage.mysql.data.entity.User;

public interface UserCommonService {
    public SessionUser getSessionUser(String email);
    public User getUser(HttpSession httpSession);
}
