package whenyourcar.application.dto.car.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CarSaleRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CarSaleRequestDto {
        @DateTimeFormat(pattern = "yyyy.MM")
        private Date age;
        private Integer aroundView;
        private Integer autoLight;
        private Integer autoPark;
        private Integer corrosion;
        private Integer cruiseCont;
        private Integer floodPartLoss;
        private Integer floodStatus;
        private Integer floodTotalLoss;
        private Integer frontCamera;
        private Integer frontSensor;
        private Integer heatBack;
        private Integer heatFront;
        private Integer heatHandle;
        private Integer illegalModification;
        private Integer insurCount;
        private Float maxOut;
        private Integer myDamageCount;
        private Integer myDamageAmount;
        private Integer otherDamageAmount;
        private Integer otherDamageCount;
        private Integer ownerChange;
        private Integer panel;
        private Integer panoSunroof;
        private Integer passAir;
        private Integer naviGen;
        private Integer naviNon;
        private Integer rearCamera;
        private Integer rearSensor;
        private Integer rearWarn;
        private Integer replaceCount;
        private Integer sunroof;
        private Integer theft;
        private Integer totalLoss;
        private Integer mileage;
        private Integer price;
        private Float torque;
        private Integer newPrice;
        private Float weight;
        private Float fuelEfficient;
        private String model;
        private String submodel;
        private String brand;
        private String number;
        private String link;
        private String name;
        private String manufacturer;
        private String color;
        @DateTimeFormat(pattern = "yyyy.MM")
        private Date firstReg;
        private String fuel;
        private String grade;
        private String description;
        private String engine;
        private Integer cc;
    }
}
