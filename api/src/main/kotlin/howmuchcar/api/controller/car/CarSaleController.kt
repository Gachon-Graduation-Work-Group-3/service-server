package howmuchcar.api.controller.car

import howmuchcar.application.converter.car.CarSaleSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.application.facade.car.CarSaleFacade
import howmuchcar.application.service.ai.AiService
import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.common.auth.CurrentUser
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.car.SearchCarsQuery
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car/sale")
class CarSaleController (
    private val carSaleFacade: CarSaleFacade,
    private val aiService: AiService
) {
    @PostMapping(
        value = ["/article"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun postSaleCar(
        @CurrentUser user: User,
        @RequestPart images: List<MultipartFile>,
        @RequestPart carSaleRequest: CarSaleRequest
    ): ApiResponse<Void> {
        carSaleFacade.postSaleCar(carSaleRequest, user, images)
        return ApiResponse.onSuccess(null)
    }

    @PatchMapping("/completed")
    fun patchCarToSaleCompleted(
        @CurrentUser user: User,
        @RequestParam carId: Long
    ): ApiResponse<Void> {
        carSaleFacade.patchCarToSaleCompleted(carId, user)
        return ApiResponse.onSuccess(null)
    }

    @GetMapping("/search/filters/info")
    fun searchInfoSaleCars(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) minAge: LocalDate?,
        @RequestParam(required = false) maxAge: LocalDate?,
        @RequestParam(required = false) minMileage: Int?,
        @RequestParam(required = false) maxMileage: Int?,
        @RequestParam(required = false) minPrice: Int?,
        @RequestParam(required = false) maxPrice: Int?,
        @RequestParam(required = false) color: String?
    ): ApiResponse<Page<CarSearchResponse>> {
        return ApiResponse.onSuccess(
            carSaleFacade.searchCars(
                PageRequest.of(page, size),
                minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color
            )
        )
    }

    @GetMapping("/search/description")
    fun searchDescription(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        @RequestParam carId: Long
    ): ApiResponse<CarSaleSearchDescResponse> {
        return ApiResponse.onSuccess(
            carSaleFacade.searchDescription(carId, httpServletRequest, httpServletResponse)
        )
    }

    @GetMapping("/search/filters/model")
    fun searchModelCars(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) manu: String?,
        @RequestParam(required = false) model: String?,
        @RequestParam(required = false) submodel: String?,
        @RequestParam(required = false) grade: String?
    ): ApiResponse<Page<CarSearchDetailResponse>> {
        return ApiResponse.onSuccess(
            carSaleFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
            )
        )
    }

    @GetMapping("/search/filters/tags")
    fun searchTagsCars(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam tag: String,
    ): ApiResponse<List<CarSearchResponse>> {
        return ApiResponse.onSuccess(
            carSaleFacade.searchTagsCars(
                page,
                size,
                tag
            )
        )
    }
}