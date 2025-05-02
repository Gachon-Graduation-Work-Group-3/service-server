package howmuchcar.application.serviceImpl.ai

import com.google.gson.JsonObject
import howmuchcar.application.service.ai.AiService
import howmuchcar.infra.openai.EmbeddingClient
import howmuchcar.infra.openai.TagGenerator
import howmuchcar.infra.pinecone.PineconeClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class AiServiceImpl(
    private val tagGenerator: TagGenerator,
    private val embeddingClient: EmbeddingClient,
    private val pineconeClient: PineconeClient
): AiService {
    override fun generateTags(description: String): List<String> {
        // GPT를 통해 태그를 추출합니다.
        return tagGenerator.generateTags(description)
    }

    override fun generateEmbedding(tags: List<String>, carId: Long): List<PineconeClient.UpsertRequest>  {
        println(tags)
        return embeddingClient.getEmbeddingsAndUpsert(tags, carId) // 임베딩 생성
    }

    override fun saveEmbedding(vector: List<PineconeClient.UpsertRequest>) {
        return pineconeClient.upsertVectors(vector)
    }

    override fun findTagCar(embed: List<Float>, page:Int, size:Int):List<Long> {
        return pineconeClient.findTagsId(embed, page, size)
    }
}