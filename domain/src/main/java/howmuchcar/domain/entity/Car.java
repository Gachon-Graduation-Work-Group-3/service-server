package howmuchcar.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @Column()
    private Float fuelEfficient;

    @Column(length = 20)
    private LocalDate first_reg;

    @Column(length = 20)
    private LocalDate age;

    @Column()
    private Integer mileage;

    @Column(length = 20)
    private String fuel;

    @Column()
    private String cc;

    @Column(name = "description", length = 500)
    private String desc;

    @Column(length = 20)
    private String engine;

    @Column()
    private Float maxOut;

    @Column()
    private Float torque;

    @Column()
    private String weight;

    @Column()
    private String brand;

    @Column()
    private String newPrice;

    @Column()
    private String manufacturer;

    @Column()
    private String model;

    @Column()
    private String submodel;

    @Column()
    private String grade;
}
