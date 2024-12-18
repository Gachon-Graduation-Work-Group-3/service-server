package whenyourcar.presentation.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarConverter;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarFacade {
    private final CarService carService;

    public Page<CarResponse.MainPageResponse> getCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carService.getCars(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarResponse.DescResponse getCarDesc(Long carId) { return carService.getCarDesc(carId); }

}
