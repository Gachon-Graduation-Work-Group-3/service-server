package howmuchcar.application.converter.user

import howmuchcar.application.dto.user.UserLikeResponse
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.User
import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.user.SearchCarLikeQuery
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class UserLikeConverter {
    fun toUserLike(car: Car, user: User) : UserLike {
        return UserLike.builder()
            .car(car)
            .user(user)
            .build()
    }

    fun toUserLikes(searchCarsQueries: Page<SearchCarLikeQuery>, userId: Long): UserLikeResponse {
        return UserLikeResponse(
            userId = userId,
            userLikeCars = searchCarsQueries
        )
    }
}