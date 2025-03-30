package howmuchcar.application.dto.chat

import howmuchcar.application.dto.car.CarSaleInfo

data class ChatRoomInfoResponse(
   val roomId: Long,
   val user2Id: Long,
   val name: String,
   val picture: String) {
}