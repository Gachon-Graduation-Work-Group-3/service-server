package howmuchcar.application.service.car

import howmuchcar.application.converter.car.CarCommonConverter
import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.port.`in`.car.CarSearchUseCase
import howmuchcar.application.port.out.db.car.CarSearchPort
import howmuchcar.domain.entity.Car
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class CarCommonServiceImpl(
    private val carSearchPort: CarSearchPort,
    private val carCommonConverter: CarCommonConverter
) :CarSearchUseCase{
    override fun getCarById(carId: Long): Car {
        return carSearchPort.findCarByCarId(carId)
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
        val search : Page<SearchCarsQuery> = carSearchPort.findTopViewCars(
            pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color
        )
        return carCommonConverter.toSearchCarsResponse(search)
    }

    override fun searchCarDescription(carId: Long): CarSearchDescResponse {
        val car = carSearchPort.findCarByCarId(carId)
        return carCommonConverter.toDescResponse(car)
    }

    override fun searchCarDetail(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        val search : Page<SearchDetailCarsQuery> = carSearchPort.findTopViewDetailCars(
            pageable, manu, model, submodel, grade
        )

        return carCommonConverter.toSearchDetailCarsResponse(search)
    }

}