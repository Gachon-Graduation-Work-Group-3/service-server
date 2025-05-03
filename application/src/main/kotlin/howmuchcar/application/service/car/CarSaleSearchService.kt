package howmuchcar.application.service.car

import howmuchcar.application.converter.car.CarSaleConverter
import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.port.`in`.car.CarSaleSearchUseCase
import howmuchcar.application.port.out.db.car.CarSalePort
import howmuchcar.domain.entity.CarSale
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@RequiredArgsConstructor
class CarSaleSearchService(
    private val carSalePort: CarSalePort,
    private val carSaleConverter: CarSaleConverter
): CarSaleSearchUseCase {
    override fun searchCarSale(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse> {
        val search = carSalePort.findTopViewCars(pageable,
            minAge,maxAge,
            minMileage, maxMileage,
            minPrice,maxPrice,
            color)

        return carSaleConverter.toSearchCarsResponse(search)
    }

    override fun searchCarSaleDescription(carSale: CarSale): CarSaleSearchDescResponse {
        return carSaleConverter.toDescResponse(carSale)
    }

    override fun searchCarSaleDetail(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        val search = carSalePort.findTopViewDetailCars(
            pageable, manu,model, submodel, grade
        )

        return carSaleConverter.toSearchDetailCarsResponse(search)
    }

    override fun findCarSaleById(carId: Long): CarSale {
        return carSalePort.findById(carId)
    }

    override fun findCarSaleByTags(idList: List<Long>): List<CarSearchResponse> {
        return carSaleConverter.toSearchCarsResponse(carSalePort.findByTagIds(idList))
    }

}