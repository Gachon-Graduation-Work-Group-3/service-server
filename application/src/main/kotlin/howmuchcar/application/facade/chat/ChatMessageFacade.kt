package howmuchcar.application.facade.chat

import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.application.service.chat.ChatMessageService
import howmuchcar.application.service.chat.ChatRoomService
import howmuchcar.application.service.user.UserCommonService
import howmuchcar.application.status.ChatErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatMessageFacade (
    private val chatRoomService: ChatRoomService,
    private val chatMessageService: ChatMessageService,
    private val userCommonService: UserCommonService
){
    fun getChatMessage(
        user: User,
        roomId: Long,
        pageable: Pageable
    ): ChatMessageResponse {
        if (chatRoomService.isUserInRoom(roomId, user.id)) {
            return chatMessageService.getChat(pageable, roomId, user.id)
        } else {
            throw RestApiException(ChatErrorStatus.NOT_EXIST_ROOM)
        }
    }

    fun sendMessageToQueue(
        roomId: Long,
        senderId: Long,
        message: String
    ) {
        val sender = userCommonService.getUserById(senderId)
        val chatId = chatMessageService.saveChat(roomId, sender, message)
        chatMessageService.sendMessageToMessageQueue(roomId, chatId, sender.id, message)
    }
}