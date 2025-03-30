package howmuchcar.application.converter.chat

import howmuchcar.application.converter.car.CarSaleConverter
import howmuchcar.application.dto.chat.ChatRoomCarInfoResponse
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class ChatCommonConverter(
    private val carSaleConverter: CarSaleConverter
) {
    fun toChatRoomResponse(query: SearchRoomInfoQuery) : ChatRoomCarInfoResponse {
        return  ChatRoomCarInfoResponse(
            roomId = query.roomId,
            user2Id = query.user2Id,
            picture = query.picture,
            name = query.name,
            carSaleInfo = carSaleConverter.toCarSaleInfoDto(query.carSale)
        )
    }
}