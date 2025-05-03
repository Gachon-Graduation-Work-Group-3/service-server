package howmuchcar.application.port.`in`.car

import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.domain.entity.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface CarSearchUseCase {

    fun getCarById(carId:Long): Car
    fun searchCars(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse>

    fun searchCarDescription(carId: Long): CarSearchDescResponse

    fun searchCarDetail(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse>
}