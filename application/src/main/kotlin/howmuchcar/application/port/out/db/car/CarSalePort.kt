package howmuchcar.application.port.out.db.car

import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface CarSalePort {
    fun save(ent: CarSale):CarSale
    fun findById(carId: Long):CarSale
    fun findTopViewCars(pageable: Pageable,
                        minAge: LocalDate?,
                        maxAge: LocalDate?,
                        minMileage: Int?,
                        maxMileage: Int?,
                        minPrice: Int?,
                        maxPrice: Int?,
                        color: String?): Page<SearchCarsQuery>

    fun findTopViewDetailCars(pageable: Pageable,
                              manu: String?,
                              model: String?,
                              submodel: String?,
                              grade: String?): Page<SearchDetailCarsQuery>

    fun increaseViewCount(carId: Long)

    fun changeToSaleCompleted(carId: Long, userId: Long)

    fun findByTagIds(idList: List<Long>): List<SearchCarsQuery>
}