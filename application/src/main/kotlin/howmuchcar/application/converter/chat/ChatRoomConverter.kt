package howmuchcar.application.converter.chat

import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.Room
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchRoomsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class ChatRoomConverter {
    fun toChatRoom(user1: User, user2: User, carSale: CarSale): Room {
        return Room.builder()
            .user1(user1)
            .user2(user2)
            .carSale(carSale)
            .build()
    }

    fun toChatCommonResponse(searchRoomsQueries: Page<SearchRoomsQuery>): Page<ChatRoomInfoResponse> {
        val chatRoomResponses: List<ChatRoomInfoResponse> = searchRoomsQueries.content.map { room: SearchRoomsQuery ->
            ChatRoomInfoResponse(
                roomId = room.roomId,
                user2Id = room.user2Id,
                picture = room.picture,
                name = room.name
            )
        }
        return PageImpl(chatRoomResponses, searchRoomsQueries.pageable, searchRoomsQueries.totalElements)
    }

}