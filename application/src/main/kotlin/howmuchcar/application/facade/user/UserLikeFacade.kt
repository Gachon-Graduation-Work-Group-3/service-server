package howmuchcar.application.facade.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.application.service.car.CarCommonService
import howmuchcar.application.service.user.UserLikeService
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLikeFacade (
    private val userLikeService: UserLikeService,
    private val carCommonService: CarCommonService
){
    fun postUserLike(user: User,
                     carId: Long){
        val car = carCommonService.getCarById(carId)
        userLikeService.postUserLike(user,car)
    }

    fun getUserLikes(pageable: Pageable,
                     user: User
    ): UserLikeResponse {
        return userLikeService.getUserLikes(user, pageable)
    }

    fun deleteUserLike(user: User,
                       userLikeId: Long) {
        userLikeService.deleteUserLike(user, userLikeId)
    }
}