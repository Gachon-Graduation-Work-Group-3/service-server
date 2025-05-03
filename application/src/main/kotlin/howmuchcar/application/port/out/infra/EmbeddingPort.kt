package howmuchcar.application.port.out.infra

import com.fasterxml.jackson.databind.JsonNode


interface EmbeddingPort {
    fun generateEmbedding(tags: List<String>): JsonNode

}