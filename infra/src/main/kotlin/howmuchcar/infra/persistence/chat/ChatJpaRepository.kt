package howmuchcar.infra.persistence.chat

import howmuchcar.domain.entity.Chat
import howmuchcar.domain.query.chat.SearchChatsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ChatJpaRepository : JpaRepository<Chat, String> {

    @Query("select new howmuchcar.domain.query.chat.SearchChatsQuery(" +
            "c.id, c.message, c.user.id, c.createdAt) " +
            "from Chat c " +
            "where c.room.id = :roomId " +
            "order by c.createdAt desc")
    fun findChatByRoomId(pageable: Pageable, @Param("roomId") roomId: Long): Page<SearchChatsQuery>
}
