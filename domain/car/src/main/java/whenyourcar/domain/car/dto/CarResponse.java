package whenyourcar.domain.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import whenyourcar.storage.mysql.data.entity.Car;

import java.time.LocalDateTime;

public class CarResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class MainPageResponse{
        private Long carId;
        private String name;
        private String age;
        private String image;
        private Integer mileage;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DescResponse{
        private Car car;
    }
}
