package howmuchcar.application.serviceImpl.car

import howmuchcar.application.service.car.CarSaleViewService
import howmuchcar.domain.repository.car.CarSaleRepository
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
class CarSaleViewServiceImpl(
    private val carSaleRepository: CarSaleRepository
) : CarSaleViewService{

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
            carSaleRepository.increaseViewCount(carId)
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