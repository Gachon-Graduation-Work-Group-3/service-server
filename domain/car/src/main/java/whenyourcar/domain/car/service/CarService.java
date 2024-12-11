package whenyourcar.domain.car.service;

import org.springframework.data.domain.Pageable;
import whenyourcar.domain.car.dto.CarResponse;

import java.util.List;

public interface CarService {
    List<CarResponse.MainPageResponse> getCars(Pageable pageable);
    CarResponse.DescResponse getCarDesc(Long carId);
}
