package howmuchcar.application.service.car

import howmuchcar.application.converter.car.CarSaleConverter
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.application.port.`in`.car.CarSaleCommonUseCase
import howmuchcar.application.port.out.db.car.CarSalePort
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CarSaleServiceImpl(
    private val carSalePort: CarSalePort,
    private val carSaleConverter: CarSaleConverter
): CarSaleCommonUseCase {

    @Transactional
    override fun postSaleCar(carSaleRequest: CarSaleRequest, user: User, imageURL: List<String>, tags: String
    ): CarSale {
        val ent = carSaleConverter.toSaleCar(carSaleRequest, user, imageURL, tags)
        return carSalePort.save(ent)
    }

    override fun patchCarToSaleCompleted(carId: Long, user: User) {
        carSalePort.changeToSaleCompleted(carId, user.id)
    }
}