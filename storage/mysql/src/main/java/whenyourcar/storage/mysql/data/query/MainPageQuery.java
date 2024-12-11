package whenyourcar.storage.mysql.data.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MainPageQuery {
    private Long carId;
    private String name;
    private String age;
    private String image;
    private Integer mileage;
}
