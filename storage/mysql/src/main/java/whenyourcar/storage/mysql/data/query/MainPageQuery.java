package whenyourcar.storage.mysql.data.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class MainPageQuery {
    private Long carId;
    private String name;
    private Date age;
    private String image;
    private Integer mileage;
    private Integer price;
}



