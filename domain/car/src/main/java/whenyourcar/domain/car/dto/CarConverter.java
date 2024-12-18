package whenyourcar.domain.car.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.query.MainPageQuery;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConverter {
    public Page<CarResponse.MainPageResponse> toMainPageResponse(Page<MainPageQuery> mainPageQueries) {
        List<CarResponse.MainPageResponse> mainPageResponses = mainPageQueries.getContent().stream()
                .map(car -> CarResponse.MainPageResponse.builder()
                        .carId(car.getCarId())
                        .age(car.getAge())
                        .name(car.getName())
                        .image(car.getImage())
                        .mileage(car.getMileage())
                        .price(car.getPrice())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(mainPageResponses, mainPageQueries.getPageable(), mainPageQueries.getTotalElements());
    }

    public CarResponse.DescResponse toDescResponse(Car car) {
        return CarResponse.DescResponse.builder()
                .car(car)
                .build();
    }
}
