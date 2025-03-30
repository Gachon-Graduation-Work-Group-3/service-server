package howmuchcar.application.service.car

import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.domain.entity.Car
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*

interface CarSaleService {
    fun postSaleCar(carSaleRequest: CarSaleRequest,
                    user: User,
                    imageURL: List<String>)

    fun patchCarToSaleCompleted(carId: Long, user: User)

    fun searchCarSale(pageable: Pageable,
                      minAge: LocalDate?,
                      maxAge: LocalDate?,
                      minMileage: Int?,
                      maxMileage: Int?,
                      minPrice: Int?,
                      maxPrice: Int?,
                      color: String?): Page<CarSearchResponse>

    fun searchCarSaleDescription(carSale: CarSale): CarSaleSearchDescResponse

    fun searchCarSaleDetail(pageable: Pageable,
                            manu: String?,
                            model: String?,
                            submodel: String?,
                            grade: String?): Page<CarSearchDetailResponse>

    fun findCarSaleById(
        carId: Long,
    ): CarSale

}