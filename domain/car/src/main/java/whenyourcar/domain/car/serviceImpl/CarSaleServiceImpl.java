package whenyourcar.domain.car.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.converter.CarSaleConverter;
import whenyourcar.domain.car.dto.CarRequest;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarSaleService;
import whenyourcar.domain.common.code.exception.AuthenticationException;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.storage.mysql.data.query.SearchCarsQuery;
import whenyourcar.storage.mysql.data.query.SearchDetailCarsQuery;
import whenyourcar.storage.mysql.repository.car.CarSaleRepository;
import whenyourcar.storage.mysql.repository.user.UserCommonRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSaleServiceImpl implements CarSaleService {
    private final CarSaleRepository carSaleRepository;
    private final UserCommonRepository userCommonRepository;
    private final CarSaleConverter carSaleConverter;

    @Override
    public void postSaleCar(CarRequest.CarSaleRequest carSaleRequest, String email) {
        User user = userCommonRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException(ErrorStatus.USER_IS_NOT_EXIST));
        System.out.println(carSaleRequest);
        System.out.println(carSaleRequest.getCc());
        carSaleRepository.save(carSaleConverter.toSaleCar(carSaleRequest, user));
    }

    @Override
    public Page<CarResponse.SearchResponse> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        Page<SearchCarsQuery> searchCarsQueries = carSaleRepository.findTopViewCars(pageable,
                minAge,maxAge,
                minMileage, maxMileage,
                minPrice,maxPrice,
                color);
        return carSaleConverter.toSearchCarsResponse(searchCarsQueries);
    }

    @Override
    public CarResponse.DescSaleResponse searchDescriptionService(Long carId) {
        return carSaleConverter.toDescResponse(carSaleRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST)));
    }

    @Override
    public Page<CarResponse.DetailSearchResponse> searchDetailCarsService(Pageable pageable,
                                                                          String manu, String model,
                                                                          String submodel, String grade) {
        Page<SearchDetailCarsQuery> searchDetailCarsQueries = carSaleRepository.findTopViewDetailCars(pageable,
                manu,model, submodel, grade);
        return carSaleConverter.toSearchDetailCarsResponse(searchDetailCarsQueries);
    }
}
