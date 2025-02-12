package whenyourcar.api.web.controller.car;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car/sale")
public class CarSeleController {
    private final CarSaleFacade carSaleFacade;
    @PostMapping(value = "/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Void> postSaleCar(
            HttpServletRequest httpServletRequest,
            @RequestPart List<MultipartFile> images,
            @RequestPart CarSaleRequest.CarSaleRequestDto carSaleRequest
            ) throws Exception {
        String email = httpServletRequest.getHeader("X-User-Email");
        carSaleFacade.postSaleCar(carSaleRequest, email, images);
        return ApiResponse.onSuccess(SuccessStatus.CAR_POST_SALE_SUCCESS, null);
    }

    @PatchMapping("/completed")
    public ApiResponse<Void> patchCarToSaleCompleted(
            HttpServletRequest httpServletRequest,
            @RequestParam Long carId
    ){
        String email = httpServletRequest.getHeader("X-User-Email");
        carSaleFacade.patchCarToSaleCompleted(carId, email);
        return ApiResponse.onSuccess(SuccessStatus.CAR_COMPLETE_SALE_SUCCESS, null);
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
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carSaleFacade.searchDescription(carId, httpServletRequest, httpServletResponse));
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
