package howmuchcar.infra.pinecone

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PineconeClient {
    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    @Value("\${pinecone.key}")
    lateinit var apiKey: String

    @Value("\${pinecone.url}")
    lateinit var indexUrl: String

    data class UpsertRequest(val id: String, val values: List<Float>, val metadata: Map<String, Any>?)

    // 벡터 업서트
    fun upsertVectors(vectors: List<UpsertRequest>) {
        val body = mapper.writeValueAsString(mapOf("vectors" to vectors))

        val request = Request.Builder()
            .url("$indexUrl/vectors/upsert") // 업서트 엔드포인트
            .header("Api-Key", apiKey)
            .post(RequestBody.create("application/json".toMediaType(), body))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Failed to upsert vectors: ${response.body?.string()}")
            } else {
                println("Successfully upserted vectors.")
            }
        }
    }

    fun findTagsId(vectors: List<Float>, page:Int, size:Int): List<Long> {
        val bodyJson = mapper.writeValueAsString(
            mapOf(
                "vector" to vectors,
                "topK" to size,
                "includeMetadata" to true
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
                return emptyList()
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
