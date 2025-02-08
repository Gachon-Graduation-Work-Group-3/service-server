package whenyourcar.api.web.controller.car;

import whenyourcar.common.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.facade.car.CarCommonFacade;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarSearchController {
    private final CarCommonFacade carCommonFacade;
    @GetMapping("/search/filters/info")
    public ApiResponse<Page<CarCommonResponse.SearchResponseDto>> searchInfoCars(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy.MM") Date minAge,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy.MM") Date maxAge,
            @RequestParam(required = false) Integer minMileage,
            @RequestParam(required = false) Integer maxMileage,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String color
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_MAIN_PAGE_SUCCESS,
                carCommonFacade.searchCars(PageRequest.of(page, size),
                        minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color));
    }

    @GetMapping("/search/description")
    public ApiResponse<CarCommonResponse.SearchDescriptionResponseDto> searchDescription(
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carCommonFacade.searchDescription(carId));
    }

    @GetMapping("/search/filters/model")
    public ApiResponse<Page<CarCommonResponse.SearchDetailResponseDto>> searchModelCars (
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) String manu,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String submodel,
            @RequestParam(required = false) String grade
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DETAIL_SUCCESS, carCommonFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
        ));
    }
}
