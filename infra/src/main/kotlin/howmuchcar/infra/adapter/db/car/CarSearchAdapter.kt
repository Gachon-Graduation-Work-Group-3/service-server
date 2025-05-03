package howmuchcar.infra.adapter.db.car

import howmuchcar.application.port.out.db.car.CarSearchPort
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.Car
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import howmuchcar.infra.persistence.car.CarCommonJpaRepository
import howmuchcar.infra.status.CarErrorStatus
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class CarSearchAdapter(
    private val carCommonJpaRepository: CarCommonJpaRepository,
) :CarSearchPort{
    override fun findTopViewCars(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<SearchCarsQuery> {
        return carCommonJpaRepository.findTopViewCars(
            pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color
        )
    }

    override fun findTopViewDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<SearchDetailCarsQuery> {
        return carCommonJpaRepository.findTopViewDetailCars(
            pageable, manu, model, submodel, grade
        )
    }

    override fun findCarByCarId(carId: Long): Car {
        return carCommonJpaRepository.findCarByCarId(carId)
            .orElseThrow{RestApiException(CarErrorStatus.NOT_EXIST_CAR)}
    }

}