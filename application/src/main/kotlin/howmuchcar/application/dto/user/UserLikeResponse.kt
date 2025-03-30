package howmuchcar.application.dto.user

import howmuchcar.domain.query.car.SearchCarsQuery
import org.springframework.data.domain.Page

data class UserLikeResponse (
    val userId: Long,
    val userLikeCars : Page<SearchCarsQuery>
){
}