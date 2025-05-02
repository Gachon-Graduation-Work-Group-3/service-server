package howmuchcar.application.converter.car

import howmuchcar.domain.entity.Car
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
class CarCommonConverter {
    fun toSearchCarsResponse(searchCarsQueries: Page<SearchCarsQuery>): Page<CarSearchResponse> {
        val searchResponses: List<CarSearchResponse> = searchCarsQueries.content
            .map { car: SearchCarsQuery ->
                CarSearchResponse(
                    carId = car.carId,
                    age = car.age,
                    name = car.name,
                    image = car.image,
                    mileage = car.mileage,
                    price = car.price,
                    tag = car.tags
                )
            }
        return PageImpl(searchResponses, searchCarsQueries.pageable, searchCarsQueries.totalElements)
    }

    fun toDescResponse(car: Car): CarSearchDescResponse {
        return CarSearchDescResponse(car = car)
    }

    fun toSearchDetailCarsResponse(searchDetailCarsQueries: Page<SearchDetailCarsQuery>): Page<CarSearchDetailResponse> {
        val searchResponses: List<CarSearchDetailResponse> =
            searchDetailCarsQueries.content
                .map{ car: SearchDetailCarsQuery ->
                    CarSearchDetailResponse(
                        carId = car.carId,
                        age = car.age,
                        name = car.name,
                        image = car.image,
                        mileage = car.mileage,
                        price = car.price,
                        tag = car.tags
                    )
                }
        return PageImpl(
            searchResponses,
            searchDetailCarsQueries.pageable,
            searchDetailCarsQueries.totalElements
        )
    }
}