package whenyourcar.application.serviceImpl.car;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.application.service.car.CarSaleViewService;
import whenyourcar.domain.repository.car.CarSaleRepository;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CarSaleViewServiceImpl implements CarSaleViewService {
    private final CarSaleRepository carSaleRepository;
    @Override
    public Cookie increaseViewCount(Long carId, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String viewedCars = "";
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("viewed_cars")) {
                    viewedCars = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }

        Set<String> viewedPostIds = new HashSet<>(Arrays.asList(viewedCars.split(",")));
        if (!viewedPostIds.contains(String.valueOf(carId))) {
            carSaleRepository.increaseViewCount(carId);
            viewedPostIds.add(String.valueOf(carId));
        }

        List<String> carList = new ArrayList<>(viewedPostIds);

        String newCookieValue = URLEncoder.encode(String.join(",", carList), StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("viewed_cars", newCookieValue);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");

        return cookie;
    }
}
