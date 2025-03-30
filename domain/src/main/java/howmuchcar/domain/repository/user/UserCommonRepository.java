package howmuchcar.domain.repository.user;


import howmuchcar.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCommonRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long userId);
    boolean existsUserByEmail(String email);
}
