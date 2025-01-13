package whenyourcar.domain.car.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.domain.car.dto.CarResponse;

import java.util.Date;

public interface CarSearchService {
    Page<CarResponse.SearchResponse> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color);
    CarResponse.DescResponse searchDescriptionService(Long carId);

    Page<CarResponse.DetailSearchResponse> searchDetailCarsService(Pageable pageable, String manu, String model, String submodel, String grade);
}
