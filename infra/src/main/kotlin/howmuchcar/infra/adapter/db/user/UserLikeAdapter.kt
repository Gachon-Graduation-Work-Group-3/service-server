package howmuchcar.infra.adapter.db.user

import howmuchcar.application.port.out.db.user.UserLikePort
import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.infra.persistence.user.UserLikeJpaRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLikeAdapter(
    private val userLikeJpaRepository: UserLikeJpaRepository
) :UserLikePort{
    override fun save(ent: UserLike): UserLike {
        return userLikeJpaRepository.save(ent)
    }

    override fun findUserLikesByUser(pageable: Pageable, userId: Long): Page<SearchCarsQuery> {
        return userLikeJpaRepository.findUserLikesByUser(pageable, userId)
    }

    override fun deleteUserLikeByIdAndUserId(userLikeId: Long, userId: Long) {
        return userLikeJpaRepository.deleteUserLikeByIdAndUserId(userLikeId, userId)
    }

}