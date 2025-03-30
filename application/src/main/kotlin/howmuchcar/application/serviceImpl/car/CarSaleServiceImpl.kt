package howmuchcar.application.serviceImpl.car

import howmuchcar.application.converter.car.CarSaleConverter
import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.application.service.car.CarSaleService
import howmuchcar.application.status.CarErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.domain.repository.car.CarSaleRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
@RequiredArgsConstructor
class CarSaleServiceImpl(
    private val carSaleRepository: CarSaleRepository,
    private val carSaleConverter: CarSaleConverter
): CarSaleService {

    @Transactional
    override fun postSaleCar(carSaleRequest: CarSaleRequest, user: User, imageURL: List<String>) {
        val ent = carSaleConverter.toSaleCar(carSaleRequest, user, imageURL)
        carSaleRepository.save(ent)
    }

    override fun patchCarToSaleCompleted(carId: Long, user: User) {
        if(carSaleRepository.changeToSaleCompleted(carId, user.id) < 1) {
            throw RestApiException(CarErrorStatus.NOT_EXIST_CAR)
        }
    }

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
        val search = carSaleRepository.findTopViewCars(pageable,
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
        val search = carSaleRepository.findTopViewDetailCars(
            pageable, manu,model, submodel, grade
        )

        return carSaleConverter.toSearchDetailCarsResponse(search)
    }

    override fun findCarSaleById(carId: Long): CarSale {
        return carSaleRepository.findById(carId)
            .orElseThrow{RestApiException(CarErrorStatus.NOT_EXIST_CAR)}
    }

}