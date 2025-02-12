package whenyourcar.application.service.car;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public interface CarSaleViewService {
    public Cookie increaseViewCount(Long carId, HttpServletRequest request);
}
