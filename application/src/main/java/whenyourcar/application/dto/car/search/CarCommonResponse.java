package whenyourcar.application.dto.car.search;

import whenyourcar.domain.entity.Car;
import whenyourcar.domain.entity.CarSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class CarCommonResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class SearchResponseDto{
        private Long carId;
        private String name;
        private Date age;
        private String image;
        private Integer mileage;
        private Integer price;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class SearchDescriptionResponseDto{
        private Car car;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class SearchDescriptionSaleResponseDto{
        private CarSale carSale;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class SearchDetailResponseDto {
        private Long carId;
        private String name;
        private Date age;
        private String image;
        private Integer mileage;
        private Integer price;
    }
}
