package whenyourcar.domain.car.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarConverter;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarService;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.query.MainPageQuery;
import whenyourcar.storage.mysql.repository.CarRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarConverter carConverter;
    @Override
    public Page<CarResponse.MainPageResponse> getCars(Pageable pageable,
                                                      Date minAge, Date maxAge,
                                                      Integer minMileage, Integer maxMileage,
                                                      Integer minPrice, Integer maxPrice,
                                                      String color) {
        Page<MainPageQuery> mainPageQuery = carRepository.findTopViewCarsForMainPage(pageable,
                minAge,maxAge,
                minMileage, maxMileage,
                minPrice,maxPrice,
                color);
        return carConverter.toMainPageResponse(mainPageQuery);
    }

    @Override
    public CarResponse.DescResponse getCarDesc(Long carId) {
        return carConverter.toDescResponse(carRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST)));
    }
}
