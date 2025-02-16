package whenyourcar.application.dto.chat.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import whenyourcar.domain.query.chat.SearchChatsQuery;

public class ChatMessageResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChatMessageResponseSetDto{
        private Long roomId;
        private String senderPicture;
        private String senderName;
        private Page<SearchChatsQuery> chatMessages;
    }

}
