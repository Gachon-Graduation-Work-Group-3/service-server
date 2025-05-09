package howmuchcar.domain.query.user;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCarLikeQuery {
    private Long likeId;
    private Long carId;
    private String name;
    private LocalDate age;
    private String image;
    private Integer mileage;
    private Integer price;
    private String tags;
}



