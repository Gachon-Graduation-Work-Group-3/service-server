package whenyourcar.application.dto.car.common;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CarSaleInfoDto {
    private String carName;
    private String image;
    private Integer price;
    private Integer mileage;
    private Date age;
    private Integer cc;
    private Integer newPrice;
    private String brand;
    private Float maxOut;
    private Float fuelEfficient;
    private Integer view;
    private boolean saleStatus;
}
