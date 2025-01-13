package whenyourcar.presentation.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.car.CarSearchFacade;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarSearchController {
    private final CarSearchFacade carSearchFacade;
    @GetMapping("/search")
    public ApiResponse<Page<CarResponse.SearchResponse>> searchCars(
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
                carSearchFacade.searchCars(PageRequest.of(page, size),
                        minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color));
    }

    @GetMapping("/description")
    public ApiResponse<CarResponse.DescResponse> searchDescription(
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carSearchFacade.searchDescription(carId));
    }

    @GetMapping("/search/detail")
    public ApiResponse<Page<CarResponse.DetailSearchResponse>> searchDetailCars (
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) String manu,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String submodel,
            @RequestParam(required = false) String grade
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DETAIL_SUCCESS, carSearchFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
        ));
    }
}
