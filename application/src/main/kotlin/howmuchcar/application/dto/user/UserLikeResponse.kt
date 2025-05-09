package howmuchcar.application.dto.user

import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.user.SearchCarLikeQuery
import org.springframework.data.domain.Page

data class UserLikeResponse (
    val userId: Long,
    val userLikeCars : Page<SearchCarLikeQuery>
){
}