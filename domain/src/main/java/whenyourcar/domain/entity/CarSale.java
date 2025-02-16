package whenyourcar.domain.entity;

import whenyourcar.domain.common.DateBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_manufacturer", columnList = "manufacturer"),
        @Index(name = "idx_model", columnList = "model"),
        @Index(name = "idx_submodel", columnList = "submodel"),
        @Index(name = "idx_grade", columnList = "grade"),
        @Index(name = "idx_price", columnList = "price"),
        @Index(name = "idx_age", columnList = "age"),
        @Index(name = "idx_color", columnList = "color"),
        @Index(name = "idx_mileage", columnList = "mileage")
})
public class CarSale extends DateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carId")
    private Long carId;

    @Column(length = 300)
    private String link;

    @Column(length = 10000)
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

    @Column()
    private Float fuelEfficient;

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
    private Integer cc;

    @Column()
    private Integer insure;

    @Column(name = "description", length = 500)
    private String desc;

    @Column(length = 20)
    private String engine;

    @Column()
    private Float maxOut;

    @Column()
    private Float torque;

    @Column()
    private Float weight;

    @Column()
    private String brand;

    @Column()
    private Integer newPrice;

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
    private Integer heatHandle;

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

    @Column()
    private Integer insurCount;

    @Column()
    private Integer ownerChange;

    @Column()
    private Integer total_loss;

    @Column()
    private Integer floodTotalLoss;

    @Column()
    private Integer floodPartLoss;

    @Column()
    private Integer theft;

    @Column()
    private Integer myDamageCount;

    @Column()
    private Integer myDamageAmount;

    @Column()
    private Integer otherDamageCount;

    @Column()
    private Integer otherDamageAmount;

    @Column()
    private Integer panel;

    @Column()
    private Integer replaceCount;

    @Column()
    private Integer corrostion;

    @Column()
    private Integer floodStatus;

    @Column()
    private Integer illegalModification;

    @Column()
    private String manufacturer;

    @Column()
    private String model;

    @Column()
    private String submodel;

    @Column()
    private String grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private boolean saleStatus;
}
