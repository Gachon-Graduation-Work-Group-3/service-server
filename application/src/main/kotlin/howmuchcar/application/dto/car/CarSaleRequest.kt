package howmuchcar.application.dto.car

import java.time.LocalDate
import java.util.Date

data class CarSaleRequest (
    val name: String,
    val images: String,
    val color: String,
    val price: Int,
    val newPrice: Int,
    val number: String,
    val fuelEfficient: Float,
    val firstReg: LocalDate,
    val age: LocalDate,
    val mileage: Int,
    val fuel: String,
    val cc: Int,
    val description: String,
    val engine: String,
    val maxOut: Float,
    val torque: Float,
    val weight: Float,
    val brand: String,
    val manufacturer: String,
    val model: String,
    val submodel: String,
    val grade: String
){
}
