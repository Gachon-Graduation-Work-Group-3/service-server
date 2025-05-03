package howmuchcar.application.port.`in`.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.User
import org.springframework.data.domain.Pageable

interface UserLikeUseCase {
    fun postUserLike(user: User, car: Car)
    fun getUserLikes(user: User, pageable: Pageable): UserLikeResponse
    fun deleteUserLike(user: User, userLikeId: Long)
}