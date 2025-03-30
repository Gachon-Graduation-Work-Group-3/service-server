package howmuchcar.domain.query.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchRoomUsersQuery {
    private Long user1Id;
    private Long user2Id;
}
