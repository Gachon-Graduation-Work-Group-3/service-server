package howmuchcar.infra.dto

data class EmbeddingRequest(
    val input: String,
    val model: String
)