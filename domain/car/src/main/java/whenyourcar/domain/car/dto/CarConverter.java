package whenyourcar.domain.car.dto;

import org.springframework.stereotype.Component;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.query.MainPageQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConverter {
    public List<CarResponse.MainPageResponse> toMainPageResponse(List<MainPageQuery> mainPageQueries) {
        return mainPageQueries.stream()
                .map( car ->
                        CarResponse.MainPageResponse.builder()
                                .carId(car.getCarId())
                                .age(car.getAge())
                                .name(car.getName())
                                .image(car.getImage())
                                .mileage(car.getMileage())
                                .price(car.getPrice()).build()
                ).collect(Collectors.toList());
    }

    public CarResponse.DescResponse toDescResponse(Car car) {
        return CarResponse.DescResponse.builder()
                .car(car)
                .build();
    }
}
