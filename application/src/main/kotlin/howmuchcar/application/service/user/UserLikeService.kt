package howmuchcar.application.service.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.User
import org.springframework.data.domain.Pageable

interface UserLikeService {
    fun postUserLike(user: User, car: Car)
    fun getUserLikes(user: User, pageable: Pageable): UserLikeResponse
    fun deleteUserLike(user: User, userLikeId: Long)
}