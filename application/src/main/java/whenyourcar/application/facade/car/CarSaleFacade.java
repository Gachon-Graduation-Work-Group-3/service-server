package whenyourcar.application.facade.car;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import whenyourcar.application.service.car.CarSaleViewService;
import whenyourcar.domain.entity.CarSale;
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
    private final CarSaleViewService carSaleViewService;

    public void postSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest,
                            String email) {
        User user = userCommonService.getUserByEmail(email);
        carSaleService.postSaleCar(carSaleRequest, user);
    }

    public Page<CarCommonResponse.SearchResponseDto> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carSaleService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarCommonResponse.SearchDescriptionSaleResponseDto searchDescription(Long carId,
                                                                                HttpServletRequest httpServletRequest,
                                                                                HttpServletResponse httpServletResponse) {
        CarSale carSale = carSaleService.findCarSaleById(carId);
        httpServletResponse.addCookie(carSaleViewService.increaseViewCount(carSale.getCarId(), httpServletRequest));
        return carSaleService.searchDescriptionService(carSale);
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carSaleService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }
}
