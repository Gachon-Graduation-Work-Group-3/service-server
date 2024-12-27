package whenyourcar.domain.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import whenyourcar.storage.mysql.data.entity.Car;

import java.time.LocalDateTime;
import java.util.Date;

public class CarResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class SearchResponse{
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
    public static class DescResponse{
        private Car car;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DetailSearchResponse {
        private Long carId;
        private String name;
        private Date age;
        private String image;
        private Integer mileage;
        private Integer price;
    }
}
