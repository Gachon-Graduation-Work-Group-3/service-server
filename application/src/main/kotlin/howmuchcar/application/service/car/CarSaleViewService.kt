package howmuchcar.application.service.car

import howmuchcar.application.port.`in`.car.CarSaleViewUseCase
import howmuchcar.application.port.out.db.car.CarSalePort
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

@Service
@RequiredArgsConstructor
class CarSaleViewService(
    private val carSalePort: CarSalePort
) : CarSaleViewUseCase {

    @Transactional
    override fun increaseViewCount(carId: Long, request: HttpServletRequest): Cookie {
        val cookies = request.cookies
        var viewedCar = ""
        if(cookies != null) {
            for(cookie in cookies) {
                if(cookie.name.equals("viewed_cars")) {
                    viewedCar = URLDecoder.decode(cookie.value, StandardCharsets.UTF_8)
                    break
                }
            }
        }

        val viewedCarSaleId = HashSet<String>(listOf(*viewedCar.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()))
        if(!viewedCarSaleId.contains(carId.toString())) {
            carSalePort.increaseViewCount(carId)
            viewedCarSaleId.add(carId.toString())
        }

        val carSaleList = listOf(viewedCarSaleId)

        val newCookieValue = URLEncoder.encode(carSaleList.joinToString(","), StandardCharsets.UTF_8)
        val cookie = Cookie("viewed_cars", newCookieValue)
        cookie.maxAge = 60 * 60 * 24
        cookie.path = "/"

        return cookie
    }

}