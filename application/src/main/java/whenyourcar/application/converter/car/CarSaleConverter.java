package whenyourcar.application.converter.car;

import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import whenyourcar.domain.query.SearchCarsQuery;
import whenyourcar.domain.query.SearchDetailCarsQuery;
import whenyourcar.application.dto.car.sale.CarSaleRequest;
import whenyourcar.application.dto.car.search.CarCommonResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarSaleConverter {
    public CarSale toSaleCar(CarSaleRequest.CarSaleRequestDto carSaleRequest, User user) {
        return CarSale.builder()
                .cc(carSaleRequest.getCc())
                .color(carSaleRequest.getColor())
                .brand(carSaleRequest.getBrand())
                .submodel(carSaleRequest.getSubmodel())
                .aroundView(carSaleRequest.getAroundView())
                .desc(carSaleRequest.getDescription())
                .cruiseCont(carSaleRequest.getCruiseCont())
                .autoPark(carSaleRequest.getAutoPark())
                .autoLight(carSaleRequest.getAutoLight())
                .corrostion(carSaleRequest.getCorrosion())
                .engine(carSaleRequest.getEngine())
                .image(carSaleRequest.getImage())
                .first_reg(carSaleRequest.getFirstReg())
                .floodPartLoss(carSaleRequest.getFloodPartLoss())
                .floodStatus(carSaleRequest.getFloodStatus())
                .floodTotalLoss(carSaleRequest.getFloodTotalLoss())
                .grade(carSaleRequest.getGrade())
                .frontCamera(carSaleRequest.getFrontCamera())
                .frontSensor(carSaleRequest.getFrontSensor())
                .link(carSaleRequest.getLink())
                .heatFront(carSaleRequest.getHeatFront())
                .name(carSaleRequest.getName())
                .heatHandle(carSaleRequest.getHeatHandle())
                .heatBack(carSaleRequest.getHeatBack())
                .insurCount(carSaleRequest.getInsurCount())
                .model(carSaleRequest.getModel())
                .age(carSaleRequest.getAge())
                .fuelEfficient(carSaleRequest.getFuelEfficient())
                .fuel(carSaleRequest.getFuel())
                .maxOut(carSaleRequest.getMaxOut())
                .illegalModification(carSaleRequest.getIllegalModification())
                .manufacturer(carSaleRequest.getManufacturer())
                .price(carSaleRequest.getPrice())
                .naviGen(carSaleRequest.getNaviGen())
                .naviNon(carSaleRequest.getNaviNon())
                .number(carSaleRequest.getNumber())
                .panel(carSaleRequest.getPanel())
                .newPrice(carSaleRequest.getNewPrice())
                .myDamageCount(carSaleRequest.getMyDamageCount())
                .otherDamageCount(carSaleRequest.getOtherDamageCount())
                .ownerChange(carSaleRequest.getOwnerChange())
                .passAir(carSaleRequest.getPassAir())
                .panoSunroof(carSaleRequest.getPanoSunroof())
                .rearCamera(carSaleRequest.getRearCamera())
                .rearSensor(carSaleRequest.getRearSensor())
                .theft(carSaleRequest.getTheft())
                .rearWarn(carSaleRequest.getRearWarn())
                .sunroof(carSaleRequest.getSunroof())
                .torque(carSaleRequest.getTorque())
                .replaceCount(carSaleRequest.getReplaceCount())
                .total_loss(carSaleRequest.getTotalLoss())
                .weight(carSaleRequest.getWeight())
                .otherDamageAmount(carSaleRequest.getOtherDamageAmount())
                .myDamageAmount(carSaleRequest.getMyDamageAmount())
                .mileage(carSaleRequest.getMileage())
                .color(carSaleRequest.getColor())
                .view(0)
                .user(user)
                .build();
    }

    public Page<CarCommonResponse.SearchResponseDto> toSearchCarsResponse(Page<SearchCarsQuery> searchCarsQueries) {
        List<CarCommonResponse.SearchResponseDto> searchResponses = searchCarsQueries.getContent().stream()
                .map(car -> CarCommonResponse.SearchResponseDto.builder()
                        .carId(car.getCarId())
                        .age(car.getAge())
                        .name(car.getName())
                        .image(car.getImage())
                        .mileage(car.getMileage())
                        .price(car.getPrice())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(searchResponses, searchCarsQueries.getPageable(), searchCarsQueries.getTotalElements());
    }

    public CarCommonResponse.SearchDescriptionSaleResponseDto toDescResponse(CarSale carSale) {
        return CarCommonResponse.SearchDescriptionSaleResponseDto.builder()
                .carSale(carSale)
                .build();
    }

    public Page<CarCommonResponse.SearchDetailResponseDto> toSearchDetailCarsResponse(Page<SearchDetailCarsQuery> searchDetailCarsQueries) {
        List<CarCommonResponse.SearchDetailResponseDto> searchResponses = searchDetailCarsQueries.getContent().stream()
                .map(car -> CarCommonResponse.SearchDetailResponseDto.builder()
                        .carId(car.getCarId())
                        .age(car.getAge())
                        .name(car.getName())
                        .image(car.getImage())
                        .mileage(car.getMileage())
                        .price(car.getPrice())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(searchResponses, searchDetailCarsQueries.getPageable(), searchDetailCarsQueries.getTotalElements());
    }
}
