package whenyourcar.domain.car.service;

import whenyourcar.storage.mysql.data.entity.Car;

public interface CarCommonService {
    public Car findCarById(Long carId);
}
