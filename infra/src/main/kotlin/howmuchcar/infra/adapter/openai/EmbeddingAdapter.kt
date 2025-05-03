package howmuchcar.infra.adapter.openai

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import howmuchcar.application.port.out.infra.EmbeddingPort
import howmuchcar.common.exception.RestApiException
import howmuchcar.infra.dto.EmbeddingRequest
import howmuchcar.infra.status.EmbeddingErrorStatus
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmbeddingAdapter : EmbeddingPort {
    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    @Value("\${openai.key}")
    lateinit var apiKey: String

    @Value("\${openai.embedding-model}")
    lateinit var model: String

    override fun generateEmbedding(tags: List<String>): JsonNode {
        val gson = Gson()
        val requestBody = EmbeddingRequest(
            input = tags.joinToString(separator = ","),
            model = model
        )

        val jsonBody = gson.toJson(requestBody)
        val mediaType = "application/json".toMediaType()
        val body = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://api.openai.com/v1/embeddings")
            .header("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            val json = response.body?.string()
                ?: throw RestApiException(EmbeddingErrorStatus.ERROR_EMBEDDING)
            val root = mapper.readTree(json)
            println(response)
            return root
        }
    }
}
