package howmuchcar.application.port.out.db.user

import howmuchcar.domain.entity.Room
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchChatsQuery
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import howmuchcar.domain.query.chat.SearchRoomUsersQuery
import howmuchcar.domain.query.chat.SearchRoomsQuery
import howmuchcar.domain.query.user.SearchOtherUserQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserPort {
    fun save(ent: User):User
    fun findByEmail(email: String): User
    fun findUserById(userId: Long): User
    fun existsUserByEmail(email: String): Boolean
}