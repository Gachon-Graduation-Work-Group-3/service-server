package whenyourcar.domain.car.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.converter.CarConverter;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarSearchService;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.query.SearchCarsQuery;
import whenyourcar.storage.mysql.data.query.SearchDetailCarsQuery;
import whenyourcar.storage.mysql.repository.car.CarSearchRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSearchServiceImpl implements CarSearchService {
    private final CarSearchRepository carSearchRepository;
    private final CarConverter carConverter;
    @Override
    public Page<CarResponse.SearchResponse> searchCarsService(Pageable pageable,
                                                      Date minAge, Date maxAge,
                                                      Integer minMileage, Integer maxMileage,
                                                      Integer minPrice, Integer maxPrice,
                                                      String color) {
        Page<SearchCarsQuery> searchCarsQueries = carSearchRepository.findTopViewCars(pageable,
                minAge,maxAge,
                minMileage, maxMileage,
                minPrice,maxPrice,
                color);
        return carConverter.toSearchCarsResponse(searchCarsQueries);
    }

    @Override
    public CarResponse.DescResponse searchDescriptionService(Long carId) {
        return carConverter.toDescResponse(carSearchRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST)));
    }

    @Override
    public Page<CarResponse.DetailSearchResponse> searchDetailCarsService(Pageable pageable,
                                                                          String manu, String model,
                                                                          String submodel, String grade) {
        Page<SearchDetailCarsQuery> searchDetailCarsQueries = carSearchRepository.findTopViewDetailCars(pageable,
                manu,model, submodel, grade);
        return carConverter.toSearchDetailCarsResponse(searchDetailCarsQueries);
    }
}
