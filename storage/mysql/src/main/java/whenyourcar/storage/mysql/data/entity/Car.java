package whenyourcar.storage.mysql.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carId")
    private Long carId;

    @Column(length = 300)
    private String link;

    @Column(length = 300)
    private String image;

    @Column(length = 20)
    private String name;

    @Column(length = 5)
    private String color;

    @Column()
    private Integer view;

    @Column()
    private Integer price;

    @Column(length = 10)
    private String number;

    @Column(length = 10)
    private String fuelEff;

    @DateTimeFormat(pattern = "yyyy.MM")
    @Column(length = 20)
    private Date first_reg;

    @DateTimeFormat(pattern = "yyyy.MM")
    @Column(length = 20)
    private Date age;

    @Column()
    private Integer mileage;

    @Column(length = 20)
    private String fuel;

    @Column()
    private String cc;

    @Column()
    private Integer insure;

    @Column(name = "description", length = 500)
    private String desc;

    @Column(length = 20)
    private String engine;

    @Column()
    private String maxOut;

    @Column()
    private String torque;

    @Column()
    private String weight;

    @Column()
    private String brand;

    @Column()
    private String newPrice;

    @Column()
    private Integer sunroof;

    @Column()
    private Integer panoSunroof;

    @Column()
    private Integer heatFront;

    @Column()
    private Integer heatBack;

    @Column()
    private Integer passAir;

    @Column()
    private Integer rearWarn;

    @Column()
    private Integer rearSensor;

    @Column()
    private Integer frontSensor;

    @Column()
    private Integer rearCamera;

    @Column()
    private Integer frontCamera;

    @Column()
    private Integer aroundView;

    @Column()
    private Integer autoLight;

    @Column()
    private Integer cruiseCont;

    @Column()
    private Integer autoPark;

    @Column()
    private Integer naviGen;

    @Column()
    private Integer naviNon;
}
