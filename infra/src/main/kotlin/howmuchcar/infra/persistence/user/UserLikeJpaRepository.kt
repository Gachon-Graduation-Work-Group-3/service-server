package howmuchcar.infra.persistence.user

import howmuchcar.domain.entity.UserLike
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.user.SearchCarLikeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserLikeJpaRepository : JpaRepository<UserLike, Long> {

    @Query("select new howmuchcar.domain.query.user.SearchCarLikeQuery(lk.id, lk.car.carId, lk.car.name, lk.car.age, lk.car.image, lk.car.mileage, lk.car.price, lk.car.tags) " +
            "from UserLike lk " +
            "where lk.user.id = :userId")
    fun findUserLikesByUser(pageable: Pageable, @Param("userId") userId: Long): Page<SearchCarLikeQuery>

    @Modifying
    @Query("delete from UserLike ul " +
            "where ul.id = :userLikeId and ul.user.id = :userId")
    fun deleteUserLikeByIdAndUserId(@Param("userLikeId") userLikeId: Long, @Param("userId") userId: Long)
}
