package howmuchcar.application.converter.car

import howmuchcar.application.dto.car.CarSaleInfo
import howmuchcar.application.dto.car.CarSaleRequest
import howmuchcar.domain.entity.CarSale
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.car.SearchCarsQuery
import howmuchcar.domain.query.car.SearchDetailCarsQuery
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component
import java.util.function.Function
import java.util.stream.Collectors

@Component
@RequiredArgsConstructor
class CarSaleConverter {
    fun toSaleCar(request: CarSaleRequest, user: User, imageURLs: List<String>) : CarSale {
        val image = imageURLs.joinToString(",")
        return CarSale.builder()
            .saleStatus(false)
            .name(request.name)
            .view(0)
            .cc(request.cc)
            .age(request.age)
            .brand(request.brand)
            .color(request.color)
            .desc(request.description)
            .engine(request.engine)
            .first_reg(request.firstReg)
            .fuelEfficient(request.fuelEfficient)
            .grade(request.grade)
            .image(image)
            .manufacturer(request.manufacturer)
            .maxOut(request.maxOut)
            .mileage(request.mileage)
            .model(request.model)
            .newPrice(request.newPrice)
            .price(request.newPrice)
            .torque(request.torque)
            .weight(request.weight)
            .submodel(request.submodel)
            .grade(request.grade)
            .number(request.number)
            .fuel(request.fuel)
            .user(user)
            .build()
    }

    fun toSearchCarsResponse(searchCarsQueries: Page<SearchCarsQuery>): Page<CarSearchResponse> {
        val searchResponses: List<CarSearchResponse> = searchCarsQueries.content
            .map{ car: SearchCarsQuery ->
                CarSearchResponse(
                    carId = car.carId,
                    age = car.age,
                    name = car.name,
                    image = car.image,
                    mileage = car.mileage,
                    price = car.price
                )
            }
        return PageImpl(searchResponses, searchCarsQueries.pageable, searchCarsQueries.totalElements)
    }

    fun toDescResponse(carSale: CarSale): CarSaleSearchDescResponse {
        return CarSaleSearchDescResponse(
            carSale = carSale
        )
    }

    fun toSearchDetailCarsResponse(searchDetailCarsQueries: Page<SearchDetailCarsQuery>): Page<CarSearchDetailResponse> {
        val searchResponses: List<CarSearchDetailResponse> = searchDetailCarsQueries.content
                .map { car: SearchDetailCarsQuery ->
                    CarSearchDetailResponse(
                        carId = car.carId,
                        age = car.age,
                        name = car.name,
                        image = car.image,
                        mileage = car.mileage,
                        price = car.price
                    )
                }
        return PageImpl(
            searchResponses,
            searchDetailCarsQueries.pageable,
            searchDetailCarsQueries.totalElements
        )
    }

    fun toCarSaleInfoDto(carSale: CarSale): CarSaleInfo {
        return CarSaleInfo(
            name = carSale.name,
            saleStatus = carSale.isSaleStatus,
            age = carSale.age,
            brand = carSale.brand,
            fuelEfficient = carSale.fuelEfficient,
            price = carSale.price,
            image = carSale.image,
            maxOut = carSale.maxOut,
            mileage = carSale.mileage,
            cc = carSale.cc,
            newPrice = carSale.newPrice,
            view = carSale.view
        )
    }
}