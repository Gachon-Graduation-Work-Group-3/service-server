package howmuchcar.infra.adapter.db.chat

import howmuchcar.application.port.out.db.chat.ChatPort
import howmuchcar.domain.entity.Chat
import howmuchcar.domain.query.chat.SearchChatsQuery
import howmuchcar.infra.persistence.chat.ChatJpaRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class ChatAdapter(
    private val chatJpaRepository: ChatJpaRepository,
) :ChatPort{

    @Transactional
    override fun save(chat: Chat): Chat {
        return chatJpaRepository.save(chat)
    }

    override fun findChatByRoomId(pageable: Pageable, roomId: Long): Page<SearchChatsQuery> {
        return chatJpaRepository.findChatByRoomId(pageable,roomId)
    }
}