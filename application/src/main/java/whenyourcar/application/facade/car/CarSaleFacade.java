package whenyourcar.application.facade.car;

import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.service.car.CarSaleService;
import whenyourcar.application.service.user.UserCommonService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSaleFacade {
    private final CarSaleService carSaleService;
    private final UserCommonService userCommonService;

    public void postSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest,
                            String email) {
        User user = userCommonService.getUserByEmail(email);
        carSaleService.postSaleCar(carSaleRequest, user);
    }

    public Page<CarCommonResponse.SearchResponseDto> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carSaleService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarCommonResponse.SearchDescriptionSaleResponseDto searchDescription(Long carId) {
        return carSaleService.searchDescriptionService(carId);
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carSaleService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }
}
