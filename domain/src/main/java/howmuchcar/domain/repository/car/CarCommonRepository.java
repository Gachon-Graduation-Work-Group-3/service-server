package howmuchcar.domain.repository.car;


import howmuchcar.domain.entity.Car;
import howmuchcar.domain.query.car.SearchCarsQuery;
import howmuchcar.domain.query.car.SearchDetailCarsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface CarCommonRepository extends JpaRepository<Car, Long> {
    @Query("SELECT new howmuchcar.domain.query.car.SearchCarsQuery(c.carId, c.name, c.age, c.image, c.mileage, c.price, c.tags) " +
            "FROM Car c " +
            "WHERE (:minAge IS NULL OR c.age >= CAST(:minAge AS date)) " +
            "AND (:maxAge IS NULL OR c.age <= CAST(:maxAge AS date)) " +
            "AND (:minMileage IS NULL OR c.mileage >= :minMileage) " +
            "AND (:maxMileage IS NULL OR c.mileage <= :maxMileage) " +
            "AND (:minPrice IS NULL OR c.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.price <= :maxPrice) " +
            "AND (:color IS NULL OR c.color = :color) " +
            "ORDER BY c.view DESC")
    Page<SearchCarsQuery> findTopViewCars(Pageable pageable,
                                          @Param("minAge") LocalDate minAge,
                                          @Param("maxAge") LocalDate maxAge,
                                          @Param("minMileage") Integer minMileage,
                                          @Param("maxMileage") Integer maxMileage,
                                          @Param("minPrice") Integer minPrice,
                                          @Param("maxPrice") Integer maxPrice,
                                          @Param("color") String color);

    @Query("select new howmuchcar.domain.query.car.SearchDetailCarsQuery(c.carId, c.name,c.age, c.image, c.mileage, c.price, c.tags) " +
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
