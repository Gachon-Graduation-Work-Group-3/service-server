package howmuchcar.application.serviceImpl.car

import howmuchcar.application.converter.car.CarCommonConverter
import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.service.car.CarCommonService
import howmuchcar.application.status.CarErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.Car
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import howmuchcar.domain.repository.car.CarCommonRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
@RequiredArgsConstructor
class CarCommonServiceImpl(
    private val carCommonRepository: CarCommonRepository,
    private val carCommonConverter: CarCommonConverter
) :CarCommonService{
    override fun getCarById(carId: Long): Car {
        return carCommonRepository.findCarByCarId(carId)
            .orElseThrow{RestApiException(CarErrorStatus.NOT_EXIST_CAR)}
    }

    override fun searchCars(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse> {
        val search : Page<SearchCarsQuery> = carCommonRepository.findTopViewCars(
            pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color
        )
        return carCommonConverter.toSearchCarsResponse(search)
    }

    override fun searchCarDescription(carId: Long): CarSearchDescResponse {
        val car = carCommonRepository.findCarByCarId(carId)
            .orElseThrow{RestApiException(CarErrorStatus.NOT_EXIST_CAR)}

        return carCommonConverter.toDescResponse(car)
    }

    override fun searchCarDetail(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        val search : Page<SearchDetailCarsQuery> = carCommonRepository.findTopViewDetailCars(
            pageable, manu, model, submodel, grade
        )

        return carCommonConverter.toSearchDetailCarsResponse(search)
    }

}