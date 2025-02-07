package whenyourcar.domain.car.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.domain.car.dto.CarRequest;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.storage.mysql.data.entity.Car;

import java.util.Date;

public interface CarSaleService {
    public void postSaleCar(CarRequest.CarSaleRequest carSaleRequest, String email);
    Page<CarResponse.SearchResponse> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color);
    CarResponse.DescSaleResponse searchDescriptionService(Long carId);
    Page<CarResponse.DetailSearchResponse> searchDetailCarsService(Pageable pageable, String manu, String model, String submodel, String grade);
}
