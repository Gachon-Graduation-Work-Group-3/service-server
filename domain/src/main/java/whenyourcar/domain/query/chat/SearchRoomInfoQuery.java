package whenyourcar.domain.query.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import whenyourcar.domain.entity.CarSale;

@Getter
@AllArgsConstructor
public class SearchRoomInfoQuery {
    private Long roomId;
    private Long user2Id;
    private String name;
    private String picture;
    private CarSale carSale;
}



