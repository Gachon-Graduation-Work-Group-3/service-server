package howmuchcar.application.port.`in`.car

import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User

interface CarSaleCommonUseCase {

    fun postSaleCar(
        carSaleRequest: CarSaleRequest,
        user: User,
        imageURL: List<String>,
        tags: String
    ): CarSale

    fun patchCarToSaleCompleted(
        carId: Long,
        user: User
    )
}
