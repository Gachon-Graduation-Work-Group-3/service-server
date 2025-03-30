package howmuchcar.application.serviceImpl.user

import howmuchcar.application.converter.user.UserLikeConverter
import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.application.service.user.UserLikeService
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.User
import howmuchcar.domain.repository.user.UserLikeRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLikeServiceImpl(
    private val userLikeRepository: UserLikeRepository,
    private val userLikeConverter: UserLikeConverter
) : UserLikeService {

    @Transactional
    override fun postUserLike(user: User, car: Car) {
        val userLike = userLikeConverter.toUserLike(car, user)
        userLikeRepository.save(userLike)
    }

    override fun getUserLikes(user: User, pageable: Pageable): UserLikeResponse {
        val search = userLikeRepository.findUserLikesByUser(pageable, user.id)
        return  userLikeConverter.toUserLikes(search, user.id)
    }

    @Transactional
    override fun deleteUserLike(user: User, userLikeId: Long) {
        userLikeRepository.deleteUserLikeByIdAndUserId(userLikeId, user.id)
    }

}