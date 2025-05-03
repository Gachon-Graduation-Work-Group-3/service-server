package howmuchcar.application.port.`in`.chat

import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.domain.entity.User
import org.springframework.data.domain.Pageable

interface ChatMessageUseCase {
    fun getChat(pageable: Pageable, roomId: Long, userId: Long): ChatMessageResponse
    fun saveChat(roomId: Long, senderId: User, message: String): String
    fun createMessageQueueForUser(userId: Long)
    fun sendMessageToMessageQueue(roomId: Long, chatId: String, senderId: Long, message: String)
}