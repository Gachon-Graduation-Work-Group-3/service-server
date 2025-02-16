package whenyourcar.application.converter.car;

import whenyourcar.domain.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import whenyourcar.domain.query.car.SearchCarsQuery;
import whenyourcar.domain.query.car.SearchDetailCarsQuery;
import whenyourcar.application.dto.car.search.CarCommonResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarCommonConverter {
    public Page<CarCommonResponse.SearchResponseDto> toSearchCarsResponse(Page<SearchCarsQuery> searchCarsQueries) {
        List<CarCommonResponse.SearchResponseDto> searchResponses = searchCarsQueries.getContent().stream()
                .map(car -> CarCommonResponse.SearchResponseDto.builder()
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

    public CarCommonResponse.SearchDescriptionResponseDto toDescResponse(Car car) {
        return CarCommonResponse.SearchDescriptionResponseDto.builder()
                .car(car)
                .build();
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> toSearchDetailCarsResponse(Page<SearchDetailCarsQuery> searchDetailCarsQueries) {
        List<CarCommonResponse.SearchDetailResponseDto> searchResponses = searchDetailCarsQueries.getContent().stream()
                .map(car -> CarCommonResponse.SearchDetailResponseDto.builder()
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
