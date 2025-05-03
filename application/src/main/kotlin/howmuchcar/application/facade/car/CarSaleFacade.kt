package howmuchcar.application.facade.car

import com.fasterxml.jackson.databind.ObjectMapper
import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.application.port.`in`.car.CarSaleCommonUseCase
import howmuchcar.application.port.`in`.car.CarSaleSearchUseCase
import howmuchcar.application.port.`in`.car.CarSaleViewUseCase
import howmuchcar.application.port.out.infra.BucketPort
import howmuchcar.application.port.out.infra.EmbeddingPort
import howmuchcar.application.port.out.infra.GenerateTagPort
import howmuchcar.application.port.out.infra.PineconePort
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*

@Service
@RequiredArgsConstructor
class CarSaleFacade (
    private val carSaleCommonUseCase: CarSaleCommonUseCase,
    private val carSaleSearchUseCase: CarSaleSearchUseCase,
    private val carSaleViewUseCase: CarSaleViewUseCase,
    private val bucketPort: BucketPort,
    private val embeddingPort: EmbeddingPort,
    private val generateTagPort: GenerateTagPort,
    private val pineconePort: PineconePort,
    private val objectMapper: ObjectMapper
){
    fun postSaleCar(
        carSaleRequest: CarSaleRequest,
        user: User,
        images: List<MultipartFile>
    ) {
        val imagesURLs: MutableList<String> = ArrayList()
        for (image in images) {
            imagesURLs.add(bucketPort.uploadFile(image))
        }
        val tags = generateTagPort.generateTags(carSaleRequest.description)
        val carSale = carSaleCommonUseCase.postSaleCar(carSaleRequest, user, imagesURLs, objectMapper.writeValueAsString(tags))

        pineconePort.upsertVectors(embeddingPort.generateEmbedding(tags), carSale.carId)
    }

    fun patchCarToSaleCompleted(carId: Long, user: User) {
        carSaleCommonUseCase.patchCarToSaleCompleted(carId, user)
    }

    fun searchCars(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse> {
        return carSaleSearchUseCase.searchCarSale(
            pageable,
            minAge,
            maxAge,
            minMileage,
            maxMileage,
            minPrice,
            maxPrice,
            color
        )
    }

    fun searchDescription(
        carId: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CarSaleSearchDescResponse {
        val carSale: CarSale = carSaleSearchUseCase.findCarSaleById(carId)
        httpServletResponse.addCookie(carSaleViewUseCase.increaseViewCount(carSale.carId, httpServletRequest))
        return carSaleSearchUseCase.searchCarSaleDescription(carSale)
    }

    fun searchDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        return carSaleSearchUseCase.searchCarSaleDetail(pageable, manu, model, submodel, grade)
    }

    fun searchTagsCars(
        page:Int,
        size:Int,
        tag: String,
    ): List<CarSearchResponse> {
        val vector = embeddingPort.generateEmbedding(listOf(tag))
        val idList =  pineconePort.findTagsId(vector, page, size)
        val idOrderMap = idList.withIndex().associate { it.value to it.index }

        val unorderedResults = carSaleSearchUseCase.findCarSaleByTags(idList)
        return unorderedResults.sortedBy { idOrderMap[it.carId] }
    }
}