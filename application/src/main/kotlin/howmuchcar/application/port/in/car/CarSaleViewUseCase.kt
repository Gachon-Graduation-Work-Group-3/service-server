package howmuchcar.application.port.`in`.car


import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest

interface CarSaleViewUseCase {
    fun increaseViewCount(carId: Long, request: HttpServletRequest): Cookie
}
