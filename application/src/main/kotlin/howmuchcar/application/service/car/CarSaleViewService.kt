package howmuchcar.application.service.car

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest

interface CarSaleViewService {
    fun increaseViewCount(carId: Long, request: HttpServletRequest): Cookie
}