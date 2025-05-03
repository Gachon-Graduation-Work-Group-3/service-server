package howmuchcar.application.port.`in`.chat

import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ChatRoomUseCase {
    fun getChatRoomCarInfo(roomId: Long, user: User): SearchRoomInfoQuery
    fun getChatRooms(pageable: Pageable, user: User): Page<ChatRoomInfoResponse>
    fun isUserInRoom(roomId: Long, userId: Long)
    fun createChatRoom(user1: User, user2: User, carSale: CarSale)
}