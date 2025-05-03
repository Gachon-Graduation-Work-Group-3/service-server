package howmuchcar.application.port.`in`.car

import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.domain.entity.CarSale
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface CarSaleSearchUseCase {

    fun searchCarSale(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse>

    fun searchCarSaleDescription(carSale: CarSale): CarSaleSearchDescResponse

    fun searchCarSaleDetail(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse>

    fun findCarSaleById(carId: Long): CarSale

    fun findCarSaleByTags(idList: List<Long>): List<CarSearchResponse>
}
