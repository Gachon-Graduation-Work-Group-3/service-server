package whenyourcar.application.service.car;

import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;

import java.util.Date;
import java.util.List;

public interface CarSaleService {
    public void postSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest, User user, List<String> imagesURLs);
    public void patchCarToSaleCompleted(Long carId, String email);
    Page<CarCommonResponse.SearchResponseDto> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color);
    CarCommonResponse.SearchDescriptionSaleResponseDto searchDescriptionService(CarSale carSale);
    Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCarsService(Pageable pageable, String manu, String model, String submodel, String grade);
    void increaseSaleCarView(Long carId);
    CarSale findCarSaleById(Long carId);
}
