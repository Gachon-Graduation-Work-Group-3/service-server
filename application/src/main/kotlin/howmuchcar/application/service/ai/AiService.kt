package howmuchcar.application.service.ai

import howmuchcar.infra.pinecone.PineconeClient


interface AiService {
    fun generateTags(description: String): List<String>
    fun generateEmbedding(tags: List<String>, carId: Long): List<PineconeClient.UpsertRequest>

    fun saveEmbedding(vector: List<PineconeClient.UpsertRequest>)

    fun findTagCar(embed: List<Float>, page:Int, size:Int):List<Long>

}