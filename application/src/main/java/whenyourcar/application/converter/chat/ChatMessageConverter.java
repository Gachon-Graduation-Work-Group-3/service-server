
package whenyourcar.application.converter.chat;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import whenyourcar.application.dto.chat.message.ChatMessageDto;
import whenyourcar.application.dto.chat.message.ChatMessageResponse;
import whenyourcar.domain.entity.Chat;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchChatsQuery;

import java.util.UUID;

@Component
public class ChatMessageConverter {
    public ChatMessageDto toChatMEssageDto(String id,
                                           Long userId,
                                           Long roomId,
                                           String message) {
        return ChatMessageDto.builder()
                .id(id)
                .message(message)
                .roomId(roomId)
                .sender(userId)
                .build();
    }

    public Chat toChatDto(Room room,
                          User user,
                          String message){
        return Chat.builder()
                .id(UUID.randomUUID().toString())
                .message(message)
                .room(room)
                .user(user)
                .build();
    }

    public ChatMessageResponse.ChatMessageResponseSetDto toGetMessageDto(
            Page<SearchChatsQuery> chats,
            Long roomId,
            User sender
    ){
        return ChatMessageResponse.ChatMessageResponseSetDto.builder()
                .chatMessages(chats)
                .senderPicture(sender.getPicture())
                .senderName(sender.getName())
                .roomId(roomId)
                .build();
    }
}
