package howmuchcar.application.service.user

import howmuchcar.application.port.`in`.user.UserCommonUseCase
import howmuchcar.application.port.out.db.user.UserPort
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserCommonServiceImpl(
    private val userPort: UserPort
) : UserCommonUseCase {

    override fun getUserById(userId: Long): User {
        val user = userPort.findUserById(userId)
        return user
    }
}