package whenyourcar.storage.mysql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.query.MainPageQuery;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository <Car, Long> {
    @Query("select new whenyourcar.storage.mysql.data.query.MainPageQuery(c.carId, c.name,c.age, c.image, c.mileage, c.price) " +
            "from Car c " +
            "where ((:minAge is null and :maxAge is null) or (c.age <= :maxAge and c.age >= :minAge))" +
            "and ((:minMileage is null and :maxMileage is null) or (c.mileage <= :maxMileage and c.mileage >= :minMileage))" +
            "and ((:minPrice is null and :maxPrice is null) or (c.price <= :maxPrice and c.price >= :minPrice))" +
            "and (:color is null or c.color = :color)" +
            "order by c.view desc ")
    Page<MainPageQuery> findTopViewCarsForMainPage(Pageable pageable,
                                                   @Param("minAge") Date minAge,
                                                   @Param("maxAge") Date maxAge,
                                                   @Param("minMileage") Integer minMileage,
                                                   @Param("maxMileage") Integer maxMileage,
                                                   @Param("minPrice") Integer minPrice,
                                                   @Param("maxPrice") Integer maxPrice,
                                                   @Param("color") String color);
}
