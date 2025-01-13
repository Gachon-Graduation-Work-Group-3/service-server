package whenyourcar.storage.mysql.repository.car;

import org.springframework.data.jpa.repository.JpaRepository;
import whenyourcar.storage.mysql.data.entity.Car;

import java.util.Optional;

public interface CarCommonRepository extends JpaRepository<Car, Long> {
    Optional<Car> findCarByCarId(Long carId);
}
