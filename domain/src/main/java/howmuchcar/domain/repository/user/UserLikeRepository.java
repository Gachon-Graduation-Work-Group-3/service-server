package howmuchcar.domain.repository.user;

import howmuchcar.domain.entity.UserLike;
import howmuchcar.domain.query.car.SearchCarsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    @Query("select new howmuchcar.domain.query.car.SearchCarsQuery(lk.car.carId, lk.car.name, lk.car.age, lk.car.image, lk.car.mileage, lk.car.price) " +
            "from UserLike lk " +
            "where lk.user.id = :userId")
    Page<SearchCarsQuery> findUserLikesByUser(Pageable pageable, @Param("userId") Long userId);

    @Modifying
    @Query("delete from UserLike ul " +
            "where ul.id = :id and ul.user.id = :userId")
    void deleteUserLikeByIdAndUserId(@Param("id") Long userLikeId,
                                        @Param("userId") Long userId);
}
