package howmuchcar.application.facade.chat

import howmuchcar.application.converter.chat.ChatCommonConverter
import howmuchcar.application.dto.chat.ChatRoomCarInfoResponse
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.application.port.`in`.car.CarSaleSearchUseCase
import howmuchcar.application.port.`in`.chat.ChatMessageUseCase
import howmuchcar.application.port.`in`.chat.ChatRoomUseCase
import howmuchcar.application.port.`in`.user.UserCommonUseCase
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatCommonFacade(
    private val chatRoomUseCase: ChatRoomUseCase,
    private val chatMessageUseCase: ChatMessageUseCase,
    private val carSaleSearchUseCase: CarSaleSearchUseCase,
    private val userCommonUseCase: UserCommonUseCase,
    private val chatCommonConverter: ChatCommonConverter
) {
    fun postChatRoom(user: User, otherUserId: Long, carId: Long) {
        val otherUser = userCommonUseCase.getUserById(otherUserId)
        val carSale = carSaleSearchUseCase.findCarSaleById(carId)

        chatRoomUseCase.createChatRoom(user, otherUser, carSale)

        chatMessageUseCase.createMessageQueueForUser(user.id)
        chatMessageUseCase.createMessageQueueForUser(otherUser.id)
    }

    fun getChatRoom(pageable: Pageable, user: User) : Page<ChatRoomInfoResponse> {
        return chatRoomUseCase.getChatRooms(pageable, user)
    }

    fun getChatRoomCarInfo(roomId: Long, user: User) : ChatRoomCarInfoResponse {
        val query = chatRoomUseCase.getChatRoomCarInfo(roomId, user)
        return chatCommonConverter.toChatRoomResponse(query)
    }
}