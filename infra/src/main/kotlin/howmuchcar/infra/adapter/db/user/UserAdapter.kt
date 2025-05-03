package howmuchcar.infra.adapter.db.user
import howmuchcar.application.port.out.db.user.UserPort
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.User
import howmuchcar.infra.persistence.user.UserCommonJpaRepository
import howmuchcar.infra.status.UserErrorStatus
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class UserAdapter(
    private val userCommonJpaRepository: UserCommonJpaRepository
) :UserPort{
    override fun save(ent: User): User {
        return userCommonJpaRepository.save(ent)
    }

    override fun findByEmail(email: String): User {
        return userCommonJpaRepository.findByEmail(email)
            .orElseThrow{ throw RestApiException(UserErrorStatus.NOT_EXIST_USER) }
    }

    override fun findUserById(userId: Long): User {
        return userCommonJpaRepository.findUserById(userId)
            .orElseThrow{ throw RestApiException(UserErrorStatus.NOT_EXIST_USER) }
    }

    override fun existsUserByEmail(email: String): Boolean {
        return userCommonJpaRepository.existsUserByEmail(email)
    }
}