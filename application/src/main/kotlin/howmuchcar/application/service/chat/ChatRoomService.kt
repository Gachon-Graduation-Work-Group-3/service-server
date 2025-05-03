package howmuchcar.application.service.chat

import howmuchcar.application.converter.chat.ChatRoomConverter
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.application.port.`in`.chat.ChatRoomUseCase
import howmuchcar.application.port.out.db.chat.ChatRoomPort
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatRoomService(
    private val chatRoomPort: ChatRoomPort,
    private val chatRoomConverter: ChatRoomConverter
) : ChatRoomUseCase {
    override fun getChatRoomCarInfo(roomId: Long, user: User): SearchRoomInfoQuery {
        return chatRoomPort.findRoomCarInfoByUserIdAndRoomId(roomId, user.id)
        //    .orElseThrow{RestApiException(ChatErrorStatus.NOT_EXIST_ROOM)}
    }

    override fun getChatRooms(pageable: Pageable, user: User): Page<ChatRoomInfoResponse> {
        val res = chatRoomPort.findPageRoomsByUserId(pageable, user.id)
        return chatRoomConverter.toChatCommonResponse(res)
    }

    override fun isUserInRoom(roomId: Long, userId: Long) {
        chatRoomPort.existsRoomByUserId(roomId, userId)
    }

    override fun createChatRoom(user1: User, user2: User, carSale: CarSale) {
        chatRoomPort.existsRoomByUsersAndCarId(user1.id, user2.id, carSale.carId)
        val ent = chatRoomConverter.toChatRoom(user1, user2, carSale)
        chatRoomPort.save(ent)
    }

}