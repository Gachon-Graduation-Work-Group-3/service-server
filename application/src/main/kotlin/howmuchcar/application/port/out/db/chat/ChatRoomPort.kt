package howmuchcar.application.port.out.db.chat

import howmuchcar.domain.entity.Room
import howmuchcar.domain.query.chat.SearchChatsQuery
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import howmuchcar.domain.query.chat.SearchRoomUsersQuery
import howmuchcar.domain.query.chat.SearchRoomsQuery
import howmuchcar.domain.query.user.SearchOtherUserQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ChatRoomPort {
    fun save(ent:Room):Room
    fun findPageRoomsByUserId(pageable: Pageable, userId: Long): Page<SearchRoomsQuery>

    fun findRoomCarInfoByUserIdAndRoomId(roomId: Long, userId: Long): SearchRoomInfoQuery

    fun findRoomsByUserId(userId: Long): List<Long>

    fun findUsersByRoomId(roomId: Long): SearchRoomUsersQuery

    fun findRoomById(roomId: Long): Room

    fun existsRoomByUserId(roomId: Long, userId: Long)

    fun existsRoomByUsersAndCarId(user1Id: Long, user2Id: Long, carId: Long)

    fun findOtherUser(roomId: Long, userId: Long): SearchOtherUserQuery
}