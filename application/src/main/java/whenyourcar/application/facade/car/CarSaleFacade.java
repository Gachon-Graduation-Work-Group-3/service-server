package whenyourcar.application.facade.car;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import whenyourcar.application.service.car.CarSaleViewService;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.service.car.CarSaleService;
import whenyourcar.application.service.user.UserCommonService;
import whenyourcar.infra.objectStorage.service.ObjectStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSaleFacade {
    private final CarSaleService carSaleService;
    private final UserCommonService userCommonService;
    private final CarSaleViewService carSaleViewService;
    private final ObjectStorageService objectStorageService;

    public void postSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest,
                            String email,
                            List<MultipartFile> images) throws Exception {
        User user = userCommonService.getUserByEmail(email);
        List<String> imagesURLs = new ArrayList<>();
        for (MultipartFile image: images) {
            imagesURLs.add(objectStorageService.uploadFile(image));
        }
        carSaleService.postSaleCar(carSaleRequest, user, imagesURLs);
    }

    public void patchCarToSaleCompleted(Long carId, String email) {
        carSaleService.patchCarToSaleCompleted(carId, email);
    }

    public Page<CarCommonResponse.SearchResponseDto> searchCars(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        return carSaleService.searchCarsService(pageable, minAge, maxAge, minMileage, maxMileage, minPrice, maxPrice,color);
    }

    public CarCommonResponse.SearchDescriptionSaleResponseDto searchDescription(Long carId,
                                                                                HttpServletRequest httpServletRequest,
                                                                                HttpServletResponse httpServletResponse) {
        CarSale carSale = carSaleService.findCarSaleById(carId);
        httpServletResponse.addCookie(carSaleViewService.increaseViewCount(carSale.getCarId(), httpServletRequest));
        return carSaleService.searchDescriptionService(carSale);
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCars(Pageable pageable, String manu, String model, String submodel, String grade ) {
        return carSaleService.searchDetailCarsService(pageable, manu, model, submodel, grade);
    }
}
