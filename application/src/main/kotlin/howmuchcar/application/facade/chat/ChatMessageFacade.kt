package howmuchcar.application.facade.chat

import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.application.port.`in`.chat.ChatMessageUseCase
import howmuchcar.application.port.`in`.chat.ChatRoomUseCase
import howmuchcar.application.port.`in`.user.UserCommonUseCase
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatMessageFacade (
    private val chatRoomUseCase: ChatRoomUseCase,
    private val chatMessageUseCase: ChatMessageUseCase,
    private val userCommonUseCase: UserCommonUseCase
){
    fun getChatMessage(
        user: User,
        roomId: Long,
        pageable: Pageable
    ): ChatMessageResponse {
        chatRoomUseCase.isUserInRoom(roomId, user.id)
        return chatMessageUseCase.getChat(pageable, roomId, user.id)
    }

    fun sendMessageToQueue(
        roomId: Long,
        senderId: Long,
        message: String
    ) {
        val sender = userCommonUseCase.getUserById(senderId)
        val chatId = chatMessageUseCase.saveChat(roomId, sender, message)
        chatMessageUseCase.sendMessageToMessageQueue(roomId, chatId, sender.id, message)
    }
}