package whenyourcar.presentation.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.car.dto.CarConverter;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.car.service.CarService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarFacade {
    private final CarService carService;

    public List<CarResponse.MainPageResponse> getCars(Pageable pageable) { return carService.getCars(pageable); }

    public CarResponse.DescResponse getCarDesc(Long carId) { return carService.getCarDesc(carId); }

}
