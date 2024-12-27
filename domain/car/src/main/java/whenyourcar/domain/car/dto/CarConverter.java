package whenyourcar.domain.car.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.query.SearchCarsQuery;
import whenyourcar.storage.mysql.data.query.SearchDetailCarsQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarConverter {
    public Page<CarResponse.SearchResponse> toSearchCarsResponse(Page<SearchCarsQuery> searchCarsQueries) {
        List<CarResponse.SearchResponse> searchResponses = searchCarsQueries.getContent().stream()
                .map(car -> CarResponse.SearchResponse.builder()
                        .carId(car.getCarId())
                        .age(car.getAge())
                        .name(car.getName())
                        .image(car.getImage())
                        .mileage(car.getMileage())
                        .price(car.getPrice())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(searchResponses, searchCarsQueries.getPageable(), searchCarsQueries.getTotalElements());
    }

    public CarResponse.DescResponse toDescResponse(Car car) {
        return CarResponse.DescResponse.builder()
                .car(car)
                .build();
    }

    public Page<CarResponse.DetailSearchResponse> toSearchDetailCarsResponse(Page<SearchDetailCarsQuery> searchDetailCarsQueries) {
        List<CarResponse.DetailSearchResponse> searchResponses = searchDetailCarsQueries.getContent().stream()
                .map(car -> CarResponse.DetailSearchResponse.builder()
                        .carId(car.getCarId())
                        .age(car.getAge())
                        .name(car.getName())
                        .image(car.getImage())
                        .mileage(car.getMileage())
                        .price(car.getPrice())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(searchResponses, searchDetailCarsQueries.getPageable(), searchDetailCarsQueries.getTotalElements());
    }
}
