package howmuchcar.application.dto.chat

import java.io.Serializable

data class ChatMessage(
    val chatId: String,
    val message: String,
    val senderId: Long
) : Serializable{
}