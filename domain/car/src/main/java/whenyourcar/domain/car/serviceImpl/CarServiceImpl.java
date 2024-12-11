package whenyourcar.domain.car.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarConverter;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarService;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.query.MainPageQuery;
import whenyourcar.storage.mysql.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarConverter carConverter;
    @Override
    public List<CarResponse.MainPageResponse> getCars(Pageable pageable) {
        List<MainPageQuery> mainPageQuery = carRepository.findTopViewCarsForMainPage(pageable);
        return carConverter.toMainPageResponse(mainPageQuery);
    }

    @Override
    public CarResponse.DescResponse getCarDesc(Long carId) {
        return carConverter.toDescResponse(carRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST)));
    }
}
