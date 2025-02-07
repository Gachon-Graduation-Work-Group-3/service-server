package whenyourcar.presentation.facade.car;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarRequest;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarSaleService;
import whenyourcar.domain.car.service.CarSearchService;
import whenyourcar.domain.user.service.UserCommonService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSaleFacade {
    private final CarSaleService carSaleService;

    public void postSaleCar(CarRequest.CarSaleRequest carSaleRequest,
                                                        HttpServletRequest request) {
        String email = request.getHeader("X-User-Email");
        carSaleService.postSaleCar(carSaleRequest, email);
    }

    public Page<CarResponse.SearchResponse> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carSaleService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarResponse.DescSaleResponse searchDescription(Long carId) {
        return carSaleService.searchDescriptionService(carId);
    }

    public Page<CarResponse.DetailSearchResponse> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carSaleService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }
}
