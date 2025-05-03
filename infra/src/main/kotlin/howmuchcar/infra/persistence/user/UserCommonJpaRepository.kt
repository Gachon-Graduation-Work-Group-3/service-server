package howmuchcar.infra.persistence.user

import howmuchcar.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserCommonJpaRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    fun findUserById(userId: Long): Optional<User>
    fun existsUserByEmail(email: String): Boolean
}
