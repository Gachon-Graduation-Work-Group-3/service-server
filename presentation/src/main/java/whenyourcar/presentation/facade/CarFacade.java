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

    public Page<CarResponse.SearchResponse> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarResponse.DescResponse searchDescription(Long carId) { return carService.searchDescriptionService(carId); }

    public Page<CarResponse.DetailSearchResponse> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }


}
