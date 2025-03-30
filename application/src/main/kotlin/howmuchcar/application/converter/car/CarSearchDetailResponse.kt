package howmuchcar.application.converter.car

import java.time.LocalDate
import java.util.*

data class CarSearchDetailResponse (
    val carId: Long,
    val name: String?,
    val age: LocalDate?,
    val image: String?,
    val mileage: Int?,
    val price: Int?
){
}