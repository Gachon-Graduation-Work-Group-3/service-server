package whenyourcar.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whenyourcar.domain.car.dto.CarResponse;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.domain.common.code.status.SuccessStatus;
import whenyourcar.presentation.apiPayload.ApiResponse;
import whenyourcar.presentation.facade.CarFacade;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarFacade carFacade;
    @GetMapping("/main")
    public ApiResponse<List<CarResponse.MainPageResponse>> getCars(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_MAIN_PAGE_SUCCESS, carFacade.getCars(PageRequest.of(page, size)));
    }

    @GetMapping("/desc")
    public ApiResponse<CarResponse.DescResponse> getCarDesc(
            @RequestParam Long carId
    ) {
        return ApiResponse.onSuccess(SuccessStatus.CAR_DESC_SUCCESS, carFacade.getCarDesc(carId));
    }
}
