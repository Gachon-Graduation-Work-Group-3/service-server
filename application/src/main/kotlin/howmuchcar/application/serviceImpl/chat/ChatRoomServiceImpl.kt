package howmuchcar.application.serviceImpl.chat

import howmuchcar.application.converter.chat.ChatRoomConverter
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.application.service.chat.ChatRoomService
import howmuchcar.application.status.ChatErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import howmuchcar.domain.repository.chat.ChatRoomRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatRoomServiceImpl(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRoomConverter: ChatRoomConverter
) : ChatRoomService {
    override fun getChatRoomCarInfo(roomId: Long, user: User): SearchRoomInfoQuery {
        val res = chatRoomRepository.findRoomCarInfoByUserIdAndRoomId(roomId, user.id)
            .orElseThrow{RestApiException(ChatErrorStatus.NOT_EXIST_ROOM)}
        return res
        //    .orElseThrow{RestApiException(ChatErrorStatus.NOT_EXIST_ROOM)}
    }

    override fun getChatRooms(pageable: Pageable, user: User): Page<ChatRoomInfoResponse> {
        val res = chatRoomRepository.findPageRoomsByUserId(pageable, user.id)
        return chatRoomConverter.toChatCommonResponse(res)
    }

    override fun isUserInRoom(roomId: Long, userId: Long): Boolean {
        return chatRoomRepository.existsRoomByUserId(roomId, userId)    }

    override fun createChatRoom(user1: User, user2: User, carSale: CarSale) {
        if(!chatRoomRepository.existsRoomByUsersAndCarId(user1.id, user2.id, carSale.carId)) {
            val ent = chatRoomConverter.toChatRoom(user1, user2, carSale)
            chatRoomRepository.save(ent)
        }else {
            throw RestApiException(ChatErrorStatus.EXIST_ROOM)
        }
    }

}