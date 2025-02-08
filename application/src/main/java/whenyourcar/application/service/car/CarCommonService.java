package whenyourcar.application.service.car;

import whenyourcar.domain.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.application.dto.car.search.CarCommonResponse;

import java.util.Date;

public interface CarCommonService {
    public Car getCarById(Long carId);
    Page<CarCommonResponse.SearchResponseDto> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color);
    CarCommonResponse.SearchDescriptionResponseDto searchDescriptionService(Long carId);
    Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCarsService(Pageable pageable, String manu, String model, String submodel, String grade);
}
