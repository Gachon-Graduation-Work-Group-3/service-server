package howmuchcar.infra.openai

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import howmuchcar.infra.dto.EmbeddingRequest
import howmuchcar.infra.pinecone.PineconeClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmbeddingClient(private val pineconeClient: PineconeClient) {
    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()

    @Value("\${openai.key}")
    lateinit var apiKey: String

    @Value("\${openai.embedding-model}")
    lateinit var model: String

    fun getEmbeddingsAndUpsert(texts: List<String>, carId: Long):  List<PineconeClient.UpsertRequest> {
        val gson = Gson()
        val requestBody = EmbeddingRequest(
            input = texts.joinToString(separator = ","),
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
            val json = response.body?.string() ?: return emptyList()
            val root = mapper.readTree(json)
            println(response)
            // OpenAI에서 반환된 임베딩 벡터를 Pinecone에 맞게 변환
            return  root["data"].mapIndexed { index, data ->
                val embedding = data["embedding"].map { it.floatValue() }
                PineconeClient.UpsertRequest(
                    id = carId.toString(),  // 각 텍스트에 대한 고유 ID
                    values = embedding,
                    metadata = mapOf("values" to texts)
                )
            }
        }
    }
}
