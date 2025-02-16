package whenyourcar.application.service.user;


import whenyourcar.domain.entity.User;

public interface UserCommonService {
    public User getUserByEmail(String email);
    public User getUserById(Long userId);
}
