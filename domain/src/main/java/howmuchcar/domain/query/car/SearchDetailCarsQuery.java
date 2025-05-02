package howmuchcar.domain.query.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SearchDetailCarsQuery {
    private Long carId;
    private String name;
    private LocalDate age;
    private String image;
    private Integer mileage;
    private Integer price;
    private String tags;
}



