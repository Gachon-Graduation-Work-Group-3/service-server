package howmuchcar.api.controller.car

import howmuchcar.application.converter.car.CarSearchDescResponse
import howmuchcar.application.converter.car.CarSearchDetailResponse
import howmuchcar.application.converter.car.CarSearchResponse
import howmuchcar.application.facade.car.CarCommonFacade
import howmuchcar.common.apiPayload.ApiResponse
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
class CarCommonController(
    private val carCommonFacade: CarCommonFacade
) {
    @GetMapping("/search/filters/info")
    fun searchInfoCars(
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
            carCommonFacade.searchCars(
                PageRequest.of(page, size),
                minAge?.withDayOfMonth(1),  // 일(day)을 1일로 설정
                maxAge?.withDayOfMonth(1),  // 일(day)을 1일로 설정
                minMileage, maxMileage, minPrice, maxPrice, color
            )
        )
    }

    @GetMapping("/search/description")
    fun searchDescription(
        @RequestParam carId: Long
    ): ApiResponse<CarSearchDescResponse> {
        return ApiResponse.onSuccess(carCommonFacade.searchDescription(carId))
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
            carCommonFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
            )
        )
    }

}