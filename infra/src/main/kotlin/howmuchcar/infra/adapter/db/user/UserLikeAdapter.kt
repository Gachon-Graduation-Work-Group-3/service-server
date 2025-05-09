package howmuchcar.infra.adapter.db.user

import howmuchcar.application.port.out.db.user.UserLikePort
import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.user.SearchCarLikeQuery
import howmuchcar.infra.persistence.user.UserLikeJpaRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserLikeAdapter(
    private val userLikeJpaRepository: UserLikeJpaRepository
) :UserLikePort{

    @Transactional
    override fun save(ent: UserLike): UserLike {
        return userLikeJpaRepository.save(ent)
    }

    override fun findUserLikesByUser(pageable: Pageable, userId: Long): Page<SearchCarLikeQuery> {
        return userLikeJpaRepository.findUserLikesByUser(pageable, userId)
    }

    @Transactional
    override fun deleteUserLikeByIdAndUserId(userLikeId: Long, userId: Long) {
        return userLikeJpaRepository.deleteUserLikeByIdAndUserId(userLikeId, userId)
    }

}