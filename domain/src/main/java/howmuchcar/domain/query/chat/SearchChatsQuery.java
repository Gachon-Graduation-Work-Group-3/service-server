package howmuchcar.domain.query.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchChatsQuery {
    private String id;
    private String message;
    private Long senderId;
    private LocalDateTime createdAt;
}



