package howmuchcar.application.facade.car

import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.port.`in`.car.CarSearchUseCase
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class CarCommonFacade (
    private val carSearchUseCase: CarSearchUseCase
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
        return carSearchUseCase.searchCars(
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
        return carSearchUseCase.searchCarDescription(carId)
    }

    fun searchDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        return carSearchUseCase.searchCarDetail(pageable, manu, model, submodel, grade)
    }
}