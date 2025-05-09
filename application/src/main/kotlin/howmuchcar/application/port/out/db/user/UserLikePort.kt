package howmuchcar.application.port.out.db.user

import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.user.SearchCarLikeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserLikePort {

    fun save(ent:UserLike):UserLike

    fun findUserLikesByUser(pageable: Pageable, userId: Long): Page<SearchCarLikeQuery>

    fun deleteUserLikeByIdAndUserId(userLikeId: Long, userId: Long)
}