package howmuchcar.infra.persistence.car

import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface CarSaleJpaRepository : JpaRepository<CarSale, Long> {

    @Query("SELECT new howmuchcar.domain.query.car.SearchCarsQuery(c.carId, c.name, c.age, c.image, c.mileage, c.price, c.tags) " +
            "FROM CarSale c " +
            "WHERE (:minAge IS NULL OR c.age >= CAST(:minAge AS date)) " +
            "AND (:maxAge IS NULL OR c.age <= CAST(:maxAge AS date)) " +
            "AND (:minMileage IS NULL OR c.mileage >= :minMileage) " +
            "AND (:maxMileage IS NULL OR c.mileage <= :maxMileage) " +
            "AND (:minPrice IS NULL OR c.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.price <= :maxPrice) " +
            "AND (:color IS NULL OR c.color = :color) " +
            "ORDER BY c.view DESC")
    fun findTopViewCars(pageable: Pageable,
                        @Param("minAge") minAge: LocalDate?,
                        @Param("maxAge") maxAge: LocalDate?,
                        @Param("minMileage") minMileage: Int?,
                        @Param("maxMileage") maxMileage: Int?,
                        @Param("minPrice") minPrice: Int?,
                        @Param("maxPrice") maxPrice: Int?,
                        @Param("color") color: String?): Page<SearchCarsQuery>

    @Query("select new howmuchcar.domain.query.car.SearchDetailCarsQuery(c.carId, c.name, c.age, c.image, c.mileage, c.price, c.tags) " +
            "from CarSale c " +
            "where ((:manu is null ) or (:manu = c.manufacturer))" +
            "and ((:model is null ) or (:model = c.model))" +
            "and ((:submodel is null ) or (:submodel = c.submodel))" +
            "and ((:grade is null ) or (:grade = c.grade))" +
            "and c.saleStatus = false ")
    fun findTopViewDetailCars(pageable: Pageable,
                              @Param("manu") manu: String?,
                              @Param("model") model: String?,
                              @Param("submodel") submodel: String?,
                              @Param("grade") grade: String?): Page<SearchDetailCarsQuery>

    @Transactional
    @Modifying
    @Query("update CarSale c " +
            "set c.view = c.view + 1 " +
            "where c.carId = :carId")
    fun increaseViewCount(@Param("carId") carId: Long)

    @Transactional
    @Modifying
    @Query("update CarSale c " +
            "set c.saleStatus = true " +
            "where c.carId = :carId and c.user.id = :userId ")
    fun changeToSaleCompleted(@Param("carId") carId: Long,
                              @Param("userId") userId: Long): Int

    @Query("select new howmuchcar.domain.query.car.SearchCarsQuery(c.carId, c.name, c.age, c.image, c.mileage, c.price, c.tags) " +
            "from CarSale c " +
            "where c.carId in :idList")
    fun findByTagIds(@Param("idList") idList: List<Long>): List<SearchCarsQuery>
}
