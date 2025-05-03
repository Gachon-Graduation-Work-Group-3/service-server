package howmuchcar.application.service.user

import howmuchcar.application.converter.user.UserLikeConverter
import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.application.port.`in`.user.UserLikeUseCase
import howmuchcar.application.port.out.db.user.UserLikePort
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.User
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLikeServiceImpl(
    private val userLikePort: UserLikePort,
    private val userLikeConverter: UserLikeConverter
) : UserLikeUseCase {

    @Transactional
    override fun postUserLike(user: User, car: Car) {
        val userLike = userLikeConverter.toUserLike(car, user)
        userLikePort.save(userLike)
    }

    override fun getUserLikes(user: User, pageable: Pageable): UserLikeResponse {
        val search = userLikePort.findUserLikesByUser(pageable, user.id)
        return  userLikeConverter.toUserLikes(search, user.id)
    }

    @Transactional
    override fun deleteUserLike(user: User, userLikeId: Long) {
        userLikePort.deleteUserLikeByIdAndUserId(userLikeId, user.id)
    }

}