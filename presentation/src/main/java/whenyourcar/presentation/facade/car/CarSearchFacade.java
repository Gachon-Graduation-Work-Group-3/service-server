package whenyourcar.presentation.facade.car;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarSearchService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSearchFacade {
    private final CarSearchService carSearchService;

    public Page<CarResponse.SearchResponse> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carSearchService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarResponse.DescResponse searchDescription(Long carId) { return carSearchService.searchDescriptionService(carId); }

    public Page<CarResponse.DetailSearchResponse> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carSearchService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }




}
