package howmuchcar.application.port.`in`.user

import howmuchcar.domain.entity.User

interface UserCommonUseCase {
    fun getUserById(userId: Long): User
}