package howmuchcar.application.service.user

import howmuchcar.domain.entity.User

interface UserCommonService {
    fun getUserById(userId: Long): User
}