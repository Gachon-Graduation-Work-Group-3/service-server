package howmuchcar.application.port.out.db.user

import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserLikePort {

    fun save(ent:UserLike):UserLike

    fun findUserLikesByUser(pageable: Pageable, userId: Long): Page<SearchCarsQuery>

    fun deleteUserLikeByIdAndUserId(userLikeId: Long, userId: Long)
}