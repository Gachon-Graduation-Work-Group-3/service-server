package howmuchcar.application.facade.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.application.port.`in`.car.CarSearchUseCase
import howmuchcar.application.port.`in`.user.UserLikeUseCase
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLikeFacade (
    private val userLikeUseCase: UserLikeUseCase,
    private val carSearchUseCase: CarSearchUseCase
){
    fun postUserLike(user: User,
                     carId: Long){
        val car = carSearchUseCase.getCarById(carId)
        userLikeUseCase.postUserLike(user,car)
    }

    fun getUserLikes(pageable: Pageable,
                     user: User
    ): UserLikeResponse {
        return userLikeUseCase.getUserLikes(user, pageable)
    }

    fun deleteUserLike(user: User,
                       userLikeId: Long) {
        userLikeUseCase.deleteUserLike(user, userLikeId)
    }
}