package howmuchcar.application.serviceImpl.user

import howmuchcar.application.service.user.UserCommonService
import howmuchcar.application.status.UserErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.User
import howmuchcar.domain.repository.user.UserCommonRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserCommonServiceImpl(
    private val userCommonRepository: UserCommonRepository
) : UserCommonService {

    override fun getUserById(userId: Long): User {
        val user = userCommonRepository.findUserById(userId)
            .orElseThrow { throw RestApiException(UserErrorStatus.NOT_EXIST_USER) }
        return user
    }
}