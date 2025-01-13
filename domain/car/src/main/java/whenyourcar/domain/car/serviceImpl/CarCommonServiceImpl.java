package whenyourcar.domain.car.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.service.CarCommonService;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.repository.car.CarCommonRepository;

@Service
@RequiredArgsConstructor
public class CarCommonServiceImpl implements CarCommonService {
    private final CarCommonRepository carCommonRepository;
    @Override
    public Car findCarById(Long carId) {
        return carCommonRepository.findCarByCarId(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST));
    }
}
