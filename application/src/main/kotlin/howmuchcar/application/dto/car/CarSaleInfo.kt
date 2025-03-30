package howmuchcar.application.dto.car

import java.time.LocalDate
import java.util.Date

data class CarSaleInfo(
    val name: String,
    val image: String,
    val price: Int,
    val mileage: Int,
    val age: LocalDate,
    val cc: Int,
    val newPrice: Int,
    val brand: String,
    val maxOut: Float,
    val fuelEfficient: Float,
    val view: Int,
    val saleStatus: Boolean
) {
}