package howmuchcar.infra.adapter.db.car

import howmuchcar.application.port.out.db.car.CarSalePort
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import howmuchcar.infra.persistence.car.CarSaleJpaRepository
import howmuchcar.infra.status.CarErrorStatus
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class CarSaleViewAdapter(
    private val carSaleJpaRepository: CarSaleJpaRepository
) : CarSalePort {
    override fun save(ent: CarSale): CarSale {
        return carSaleJpaRepository.save(ent)
    }

    override fun findById(carId: Long): CarSale {
        return carSaleJpaRepository.findById(carId)
            .orElseThrow{throw RestApiException(CarErrorStatus.NOT_EXIST_CAR)}
    }

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
        return carSaleJpaRepository.findTopViewCars(pageable,
            minAge,maxAge,
            minMileage, maxMileage,
            minPrice,maxPrice,
            color)
    }

    override fun findTopViewDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<SearchDetailCarsQuery> {
        return carSaleJpaRepository.findTopViewDetailCars(
            pageable, manu,model, submodel, grade
        )
    }

    override fun increaseViewCount(carId: Long) {
        carSaleJpaRepository.increaseViewCount(carId)
    }

    override fun changeToSaleCompleted(carId: Long, userId: Long) {
        if(carSaleJpaRepository.changeToSaleCompleted(carId, userId) <1) {
            throw RestApiException(CarErrorStatus.NOT_EXIST_CAR)
        }
    }

    override fun findByTagIds(idList: List<Long>): List<SearchCarsQuery> {
        return carSaleJpaRepository.findByTagIds(idList)
    }

}