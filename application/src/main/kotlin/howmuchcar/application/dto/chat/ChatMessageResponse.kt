package howmuchcar.application.dto.chat

import howmuchcar.domain.query.chat.SearchChatsQuery
import org.springframework.data.domain.Page

data class ChatMessageResponse (
    val roomId: Long,
    val senderPicture: String,
    val senderName: String,
    val chatMessages: Page<SearchChatsQuery>
){
}