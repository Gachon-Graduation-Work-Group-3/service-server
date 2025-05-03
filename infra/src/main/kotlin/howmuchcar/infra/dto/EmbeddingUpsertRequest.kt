package howmuchcar.infra.dto

data class EmbeddingUpsertRequest(
    val id: String,
    val values: List<Float>
)