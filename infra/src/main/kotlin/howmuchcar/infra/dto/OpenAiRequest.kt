package howmuchcar.infra.dto
data class OpenAiRequest(
    val model: String,
    val messages: List<OpenAiMessage>
)