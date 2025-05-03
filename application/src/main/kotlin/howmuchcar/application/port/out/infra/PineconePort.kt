package howmuchcar.application.port.out.infra

import com.fasterxml.jackson.databind.JsonNode


interface PineconePort {
    fun upsertVectors(vectors: JsonNode, carId: Long)
    fun findTagsId(vectors: JsonNode, page:Int, size:Int): List<Long>
}