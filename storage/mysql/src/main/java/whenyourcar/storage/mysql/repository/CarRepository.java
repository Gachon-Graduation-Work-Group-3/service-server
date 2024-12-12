package whenyourcar.storage.mysql.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.query.MainPageQuery;

import java.util.List;

public interface CarRepository extends JpaRepository <Car, Long> {
    @Query("select new whenyourcar.storage.mysql.data.query.MainPageQuery(c.carId, c.name,c.age, c.image, c.mileage, c.price) " +
            "from Car c " +
            "order by c.view desc ")
    List<MainPageQuery> findTopViewCarsForMainPage(Pageable pageable);
}
