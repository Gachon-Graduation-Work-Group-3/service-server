package whenyourcar.domain.repository.car;


import whenyourcar.domain.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whenyourcar.domain.query.car.SearchCarsQuery;
import whenyourcar.domain.query.car.SearchDetailCarsQuery;

import java.util.Date;
import java.util.Optional;

public interface CarCommonRepository extends JpaRepository<Car, Long> {
    @Query("select new whenyourcar.domain.query.car.SearchCarsQuery(c.carId, c.name,c.age, c.image, c.mileage, c.price) " +
            "from Car c " +
            "where ((:minAge is null and :maxAge is null) or (c.age <= :maxAge and c.age >= :minAge))" +
            "and ((:minMileage is null and :maxMileage is null) or (c.mileage <= :maxMileage and c.mileage >= :minMileage))" +
            "and ((:minPrice is null and :maxPrice is null) or (c.price <= :maxPrice and c.price >= :minPrice))" +
            "and (:color is null or c.color = :color)" +
            "order by c.view desc ")
    Page<SearchCarsQuery> findTopViewCars(Pageable pageable,
                                          @Param("minAge") Date minAge,
                                          @Param("maxAge") Date maxAge,
                                          @Param("minMileage") Integer minMileage,
                                          @Param("maxMileage") Integer maxMileage,
                                          @Param("minPrice") Integer minPrice,
                                          @Param("maxPrice") Integer maxPrice,
                                          @Param("color") String color);

    @Query("select new whenyourcar.domain.query.car.SearchDetailCarsQuery(c.carId, c.name,c.age, c.image, c.mileage, c.price) " +
            "from Car c " +
            "where ((:manu is null ) or (:manu = c.manufacturer))" +
            "and ((:model is null ) or (:model = c.model))" +
            "and ((:submodel is null ) or (:submodel = c.submodel))" +
            "and ((:grade is null ) or (:grade = c.grade))")
    Page<SearchDetailCarsQuery> findTopViewDetailCars(Pageable pageable,
                                                      @Param("manu") String manu,
                                                      @Param("model") String model,
                                                      @Param("submodel") String submodel,
                                                      @Param("grade") String grade);

    Optional<Car> findCarByCarId(Long carId);
}
