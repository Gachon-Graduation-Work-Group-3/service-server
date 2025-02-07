package whenyourcar.presentation.controller.car;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import whenyourcar.domain.car.dto.CarRequest;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.car.CarSaleFacade;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car/sell")
public class CarSeleController {
    private final CarSaleFacade carSaleFacade;
    @PostMapping("/")
    public ApiResponse<Void> postSaleCar(
            @RequestBody CarRequest.CarSaleRequest carSaleRequest,
            HttpServletRequest request
            ) {
        carSaleFacade.postSaleCar(carSaleRequest, request);
        return ApiResponse.onSuccess(SuccessStatus.CAR_POST_SALE_SUCCESS, null);
    }

    @GetMapping("/search")
    public ApiResponse<Page<CarResponse.SearchResponse>> getSaleCar(
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
                carSaleFacade.searchCars(PageRequest.of(page, size),
                        minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice, color));
    }

    @GetMapping("/description")
    public ApiResponse<CarResponse.DescSaleResponse> searchDescription(
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carSaleFacade.searchDescription(carId));
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
        return ApiResponse.onSuccess(SuccessStatus.CAR_DETAIL_SUCCESS, carSaleFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
        ));
    }

}
