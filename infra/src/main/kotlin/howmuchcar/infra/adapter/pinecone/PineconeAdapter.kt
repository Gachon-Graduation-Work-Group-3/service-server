package howmuchcar.infra.adapter.pinecone

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import howmuchcar.application.port.out.infra.PineconePort
import howmuchcar.common.exception.RestApiException
import howmuchcar.infra.dto.EmbeddingUpsertRequest
import howmuchcar.infra.status.PineconeErrorStatus
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PineconeAdapter : PineconePort {
    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    @Value("\${pinecone.key}")
    lateinit var apiKey: String

    @Value("\${pinecone.url}")
    lateinit var indexUrl: String

    // 벡터 업서트
    override fun upsertVectors(vectors: JsonNode, carId: Long) {
        vectors["data"].mapIndexed { index, data ->
            val embedding = data["embedding"].map { it.floatValue() }
            EmbeddingUpsertRequest(
                id = carId.toString(),  // 각 텍스트에 대한 고유 ID
                values = embedding
            )
        }
        val body = mapper.writeValueAsString(mapOf("vectors" to vectors))

        val request = Request.Builder()
            .url("$indexUrl/vectors/upsert") // 업서트 엔드포인트
            .header("Api-Key", apiKey)
            .post(RequestBody.create("application/json".toMediaType(), body))
            .build()

        client.newCall(request).execute().use { response ->
            if (response.code != 200) {
                throw RestApiException(PineconeErrorStatus.ERROR_SAVING_PINECONE)
            } else {
                println("Successfully upserted vectors.")
            }
        }
    }

    override fun findTagsId(vectors: JsonNode, page:Int, size:Int): List<Long> {
        val embedding = vectors["data"].map { data ->
            data["embedding"].map{it.floatValue()}
        }
        val bodyJson = mapper.writeValueAsString(
            mapOf(
                "vector" to embedding,
                "topK" to size
            )
        )

        val request = Request.Builder()
            .url("$indexUrl/query")
            .header("Api-Key", apiKey)
            .post(RequestBody.create("application/json".toMediaType(), bodyJson))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Query failed: ${response.body?.string()}")
                throw RestApiException(PineconeErrorStatus.ERROR_LOADING_PINECONE)
            }

            val json = response.body?.string() ?: return emptyList()
            val root = mapper.readTree(json)
            val matches = root["matches"] ?: return emptyList()

            return matches.mapNotNull { match ->
                match["id"]?.asText()?.toLongOrNull()
            }
        }
    }
}
