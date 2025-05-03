package howmuchcar.application.port.out.db.chat

import howmuchcar.domain.entity.Chat
import howmuchcar.domain.query.chat.SearchChatsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ChatPort {

    fun save(chat: Chat):Chat
    fun findChatByRoomId(pageable: Pageable,
                         roomId: Long
    ): Page<SearchChatsQuery>
}