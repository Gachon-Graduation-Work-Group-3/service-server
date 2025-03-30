package howmuchcar.application.facade.car

import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.application.service.car.CarSaleService
import howmuchcar.application.service.car.CarSaleViewService
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.infra.service.ObjectStorageService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*

@Service
@RequiredArgsConstructor
class CarSaleFacade (
    private val carSaleService: CarSaleService,
    private val objectStorageService: ObjectStorageService,
    private val carSaleViewService: CarSaleViewService
){
    fun postSaleCar(
        carSaleRequest: CarSaleRequest,
        user: User,
        images: List<MultipartFile>
    ) {
        val imagesURLs: MutableList<String> = ArrayList()
        for (image in images) {
            imagesURLs.add(objectStorageService.uploadFile(image))
        }
        carSaleService.postSaleCar(carSaleRequest, user, imagesURLs)
    }

    fun patchCarToSaleCompleted(carId: Long, user: User) {
        carSaleService.patchCarToSaleCompleted(carId, user)
    }

    fun searchCars(
        pageable: Pageable,
        minAge: LocalDate?,
        maxAge: LocalDate?,
        minMileage: Int?,
        maxMileage: Int?,
        minPrice: Int?,
        maxPrice: Int?,
        color: String?
    ): Page<CarSearchResponse> {
        return carSaleService.searchCarSale(
            pageable,
            minAge,
            maxAge,
            minMileage,
            maxMileage,
            minPrice,
            maxPrice,
            color
        )
    }

    fun searchDescription(
        carId: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CarSaleSearchDescResponse {
        val carSale: CarSale = carSaleService.findCarSaleById(carId)
        httpServletResponse.addCookie(carSaleViewService.increaseViewCount(carSale.carId, httpServletRequest))
        return carSaleService.searchCarSaleDescription(carSale)
    }

    fun searchDetailCars(
        pageable: Pageable,
        manu: String?,
        model: String?,
        submodel: String?,
        grade: String?
    ): Page<CarSearchDetailResponse> {
        return carSaleService.searchCarSaleDetail(pageable, manu, model, submodel, grade)
    }
}