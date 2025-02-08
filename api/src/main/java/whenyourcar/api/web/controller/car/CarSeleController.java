package whenyourcar.api.web.controller.car;

import whenyourcar.common.annotation.EmailParam;
import whenyourcar.common.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.facade.car.CarSaleFacade;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car/sale")
public class CarSeleController {
    private final CarSaleFacade carSaleFacade;
    @PostMapping("/article")
    public ApiResponse<Void> postSaleCar(
            @EmailParam String email,
            @RequestBody CarSaleRequest.CarSaleRequestDto carSaleRequest
            ) {
        carSaleFacade.postSaleCar(carSaleRequest, email);
        return ApiResponse.onSuccess(SuccessStatus.CAR_POST_SALE_SUCCESS, null);
    }

    @GetMapping("/search/filters/info")
    public ApiResponse<Page<CarCommonResponse.SearchResponseDto>> searchInfoSaleCars(
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

    @GetMapping("/search/description")
    public ApiResponse<CarCommonResponse.SearchDescriptionSaleResponseDto> searchDescription(
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carSaleFacade.searchDescription(carId));
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
        return ApiResponse.onSuccess(SuccessStatus.CAR_DETAIL_SUCCESS, carSaleFacade.searchDetailCars(
                PageRequest.of(page, size),
                manu, model, submodel, grade
        ));
    }

}
