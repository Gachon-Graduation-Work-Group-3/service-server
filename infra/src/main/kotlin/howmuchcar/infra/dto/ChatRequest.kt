package howmuchcar.infra.dto
data class ChatRequest(
    val model: String,
    val messages: List<ChatMessage>
)