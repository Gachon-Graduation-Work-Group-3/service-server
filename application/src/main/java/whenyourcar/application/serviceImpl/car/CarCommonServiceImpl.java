package whenyourcar.application.serviceImpl.car;

import whenyourcar.common.code.exception.GeneralException;
import whenyourcar.common.code.status.ErrorStatus;
import whenyourcar.domain.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.query.car.SearchCarsQuery;
import whenyourcar.domain.query.car.SearchDetailCarsQuery;
import whenyourcar.domain.repository.car.CarCommonRepository;
import whenyourcar.application.converter.car.CarCommonConverter;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.service.car.CarCommonService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarCommonServiceImpl implements CarCommonService {
    private final CarCommonRepository carCommonRepository;
    private final CarCommonConverter carCommonConverter;

    @Override
    public Car getCarById(Long carId) {
        return carCommonRepository.findCarByCarId(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST));
    }

    @Override
    public Page<CarCommonResponse.SearchResponseDto> searchCarsService(Pageable pageable,
                                                                       Date minAge, Date maxAge,
                                                                       Integer minMileage, Integer maxMileage,
                                                                       Integer minPrice, Integer maxPrice,
                                                                       String color) {
        Page<SearchCarsQuery> searchCarsQueries = carCommonRepository.findTopViewCars(pageable,
                minAge,maxAge,
                minMileage, maxMileage,
                minPrice,maxPrice,
                color);
        return carCommonConverter.toSearchCarsResponse(searchCarsQueries);
    }

    @Override
    public CarCommonResponse.SearchDescriptionResponseDto searchDescriptionService(Long carId) {
        return carCommonConverter.toDescResponse(carCommonRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST)));
    }

    @Override
    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCarsService(Pageable pageable,
                                                                                   String manu, String model,
                                                                                   String submodel, String grade) {
        Page<SearchDetailCarsQuery> searchDetailCarsQueries = carCommonRepository.findTopViewDetailCars(pageable,
                manu,model, submodel, grade);
        return carCommonConverter.toSearchDetailCarsResponse(searchDetailCarsQueries);
    }
}
