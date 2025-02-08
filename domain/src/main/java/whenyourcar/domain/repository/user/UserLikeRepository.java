package whenyourcar.domain.repository.user;

import whenyourcar.domain.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whenyourcar.domain.query.SearchCarsQuery;


public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    @Query("select new whenyourcar.domain.query.SearchCarsQuery(lk.car.carId, lk.car.name, lk.car.age, lk.car.image, lk.car.mileage, lk.car.price) " +
            "from UserLike lk " +
            "where lk.user.id = :userId")
    Page<SearchCarsQuery> findUserLikesByUser(Pageable pageable, @Param("userId") Long userId);

    @Modifying
    @Query("delete from UserLike ul " +
            "where ul.id = :id and ul.user.email = :email")
    void deleteUserLikeByIdAndUserEmail(@Param("id") Long userLikeId,
                                        @Param("email") String email);
}
