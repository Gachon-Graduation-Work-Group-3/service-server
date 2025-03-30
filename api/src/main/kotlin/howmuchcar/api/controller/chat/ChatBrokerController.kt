package howmuchcar.api.controller.chat

import howmuchcar.application.facade.chat.ChatMessageFacade
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
@RequiredArgsConstructor
class ChatBrokerController(
    private val chatMessageFacade: ChatMessageFacade
) {
    @MessageMapping("/{roomId}/{senderId}")
    fun postMessageToRedisStream(@DestinationVariable roomId: Long,
                                 @DestinationVariable senderId: Long,
                                 message: String ) {
        chatMessageFacade.sendMessageToQueue(roomId, senderId, message)
    }
}