package whenyourcar.application.serviceImpl.car;

import whenyourcar.common.code.exception.GeneralException;
import whenyourcar.common.code.status.ErrorStatus;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.domain.query.SearchCarsQuery;
import whenyourcar.domain.query.SearchDetailCarsQuery;
import whenyourcar.domain.repository.car.CarSaleRepository;
import whenyourcar.application.converter.car.CarSaleConverter;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;
import whenyourcar.application.service.car.CarSaleService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarSaleServiceImpl implements CarSaleService {
    private final CarSaleRepository carSaleRepository;
    private final CarSaleConverter carSaleConverter;

    @Override
    public void postSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest, User user) {
        carSaleRepository.save(carSaleConverter.toSaleCar(carSaleRequest, user));
    }

    @Override
    public void patchCarToSaleCompleted(Long carId, String email) {
        if (carSaleRepository.changeToSaleCompleted(carId, email) < 1){
            throw new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST);
        }
    }

    @Override
    public Page<CarCommonResponse.SearchResponseDto> searchCarsService(Pageable pageable, Date minAge, Date maxAge, Integer minMileage, Integer maxMileage, Integer minPrice, Integer maxPrice, String color) {
        Page<SearchCarsQuery> searchCarsQueries = carSaleRepository.findTopViewCars(pageable,
                minAge,maxAge,
                minMileage, maxMileage,
                minPrice,maxPrice,
                color);
        return carSaleConverter.toSearchCarsResponse(searchCarsQueries);
    }

    @Override
    public CarCommonResponse.SearchDescriptionSaleResponseDto searchDescriptionService(CarSale carSale) {
        return carSaleConverter.toDescResponse(carSale);
    }

    @Override
    public Page<CarCommonResponse.SearchDetailResponseDto> searchDetailCarsService(Pageable pageable,
                                                                                   String manu, String model,
                                                                                   String submodel, String grade) {
        Page<SearchDetailCarsQuery> searchDetailCarsQueries = carSaleRepository.findTopViewDetailCars(pageable,
                manu,model, submodel, grade);
        return carSaleConverter.toSearchDetailCarsResponse(searchDetailCarsQueries);
    }

    @Override
    public void increaseSaleCarView(Long carId) {
        carSaleRepository.increaseViewCount(carId);
    }

    @Override
    public CarSale findCarSaleById(Long carId) {
        return carSaleRepository.findById(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST));
    }


}
