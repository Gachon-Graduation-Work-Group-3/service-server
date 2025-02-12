package whenyourcar.application.facade.car;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.service.car.CarCommonService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarCommonFacade {
    private final CarCommonService carCommonService;

    public Page<CarCommonResponse.SearchResponseDto> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carCommonService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarCommonResponse.SearchDescriptionResponseDto searchDescription(Long carId) {
        return carCommonService.searchDescriptionService(carId);
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carCommonService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }

}
