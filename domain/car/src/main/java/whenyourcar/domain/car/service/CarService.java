package whenyourcar.domain.car.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.domain.car.dto.CarResponse;

import java.util.Date;
import java.util.List;

public interface CarService {
    Page<CarResponse.MainPageResponse> getCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color);
    CarResponse.DescResponse getCarDesc(Long carId);
}
