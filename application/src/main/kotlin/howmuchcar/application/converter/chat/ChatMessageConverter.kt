package howmuchcar.application.converter.chat

import howmuchcar.application.dto.chat.ChatMessage
import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.domain.entity.Chat
import howmuchcar.domain.entity.Room
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchChatsQuery
import howmuchcar.domain.query.user.SearchOtherUserQuery
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.*

@Component
class ChatMessageConverter {
    fun toChatMessage(id: String, userId: Long, roomId: Long, message:String) : ChatMessage {
        return ChatMessage(
            chatId = id,
            message = message,
            senderId = userId
        )
    }

    fun toChat(room: Room, user: User, message:String): Chat {
        return Chat.builder()
            .id(UUID.randomUUID().toString())
            .message(message)
            .room(room)
            .user(user)
            .build()
    }

    fun toGetMessageDto(
        chats: Page<SearchChatsQuery>,
        roomId: Long,
        sender: SearchOtherUserQuery
    ): ChatMessageResponse {
        return ChatMessageResponse(
            chatMessages = chats,
            senderPicture = sender.picture,
            senderName = sender.name,
            roomId = roomId
        )
    }
}