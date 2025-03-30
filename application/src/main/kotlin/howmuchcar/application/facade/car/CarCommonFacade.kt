package howmuchcar.application.facade.car

import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.service.car.CarCommonService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
@RequiredArgsConstructor
class CarCommonFacade (
    private val carCommonService: CarCommonService
){
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
        return carCommonService.searchCars(
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

    fun searchDescription(carId: Long): CarSearchDescResponse {
        return carCommonService.searchCarDescription(carId)
    }

    fun searchDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        return carCommonService.searchCarDetail(pageable, manu, model, submodel, grade)
    }
}