package howmuchcar.infra.adapter.db.chat

import howmuchcar.application.port.out.db.chat.ChatRoomPort
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.Room
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import howmuchcar.domain.query.chat.SearchRoomUsersQuery
import howmuchcar.domain.query.chat.SearchRoomsQuery
import howmuchcar.domain.query.user.SearchOtherUserQuery
import howmuchcar.infra.persistence.chat.ChatRoomJpaRepository
import howmuchcar.infra.status.ChatErrorStatus
import howmuchcar.infra.status.UserErrorStatus
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatRoomAdapter(
    private val chatRoomJpaRepository: ChatRoomJpaRepository,
) :ChatRoomPort{
    override fun save(ent: Room): Room {
        return chatRoomJpaRepository.save(ent)
    }

    override fun findPageRoomsByUserId(pageable: Pageable, userId: Long): Page<SearchRoomsQuery> {
        return chatRoomJpaRepository.findPageRoomsByUserId(pageable, userId)
    }

    override fun findRoomCarInfoByUserIdAndRoomId(roomId: Long, userId: Long): SearchRoomInfoQuery {
        return  chatRoomJpaRepository.findRoomCarInfoByUserIdAndRoomId(roomId, userId)
            .orElseThrow{ throw RestApiException(ChatErrorStatus.NOT_EXIST_ROOM) }
    }

    override fun findRoomsByUserId(userId: Long): List<Long> {
        return chatRoomJpaRepository.findRoomsByUserId(userId)
    }

    override fun findUsersByRoomId(roomId: Long): SearchRoomUsersQuery {
        return chatRoomJpaRepository.findUsersByRoomId(roomId)
    }

    override fun findRoomById(roomId: Long): Room {
        return chatRoomJpaRepository.findRoomById(roomId)
            .orElseThrow{ throw RestApiException(ChatErrorStatus.NOT_EXIST_ROOM) }
    }

    override fun existsRoomByUserId(roomId: Long, userId: Long) {
        if(!chatRoomJpaRepository.existsRoomByUserId(roomId, userId)){
            throw RestApiException(ChatErrorStatus.NOT_EXIST_ROOM)
        }
    }

    override fun existsRoomByUsersAndCarId(user1Id: Long, user2Id: Long, carId: Long) {
        if(chatRoomJpaRepository.existsRoomByUsersAndCarId(user1Id, user2Id, carId)) {
            throw RestApiException(ChatErrorStatus.EXIST_ROOM)
        }
    }

    override fun findOtherUser(roomId: Long, userId: Long): SearchOtherUserQuery {
        return chatRoomJpaRepository.findOtherUser(roomId, userId)
            .orElseThrow{ throw RestApiException(UserErrorStatus.NOT_EXIST_USER) }

    }
}