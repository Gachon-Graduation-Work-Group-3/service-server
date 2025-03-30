package howmuchcar.domain.query.chat;

import howmuchcar.domain.entity.CarSale;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchRoomInfoQuery {
    private Long roomId;
    private Long user2Id;
    private String name;
    private String picture;
    private CarSale carSale;
}



