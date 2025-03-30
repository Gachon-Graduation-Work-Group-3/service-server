package howmuchcar.application.facade.chat

import howmuchcar.application.converter.chat.ChatCommonConverter
import howmuchcar.application.dto.chat.ChatRoomCarInfoResponse
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.application.service.car.CarSaleService
import howmuchcar.application.service.chat.ChatMessageService
import howmuchcar.application.service.chat.ChatRoomService
import howmuchcar.application.service.user.UserCommonService
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatCommonFacade(
    private val chatRoomService: ChatRoomService,
    private val chatMessageService: ChatMessageService,
    private val carSaleService: CarSaleService,
    private val userCommonService: UserCommonService,
    private val chatCommonConverter: ChatCommonConverter
) {
    fun postChatRoom(user: User, otherUserId: Long, carId: Long) {
        val otherUser = userCommonService.getUserById(otherUserId)
        val carSale = carSaleService.findCarSaleById(carId)

        chatRoomService.createChatRoom(user, otherUser, carSale)

        chatMessageService.createMessageQueueForUser(user.id)
        chatMessageService.createMessageQueueForUser(otherUser.id)
    }

    fun getChatRoom(pageable: Pageable, user: User) : Page<ChatRoomInfoResponse> {
        return chatRoomService.getChatRooms(pageable, user)
    }

    fun getChatRoomCarInfo(roomId: Long, user: User) : ChatRoomCarInfoResponse {
        val query = chatRoomService.getChatRoomCarInfo(roomId, user)
        return chatCommonConverter.toChatRoomResponse(query)
    }
}