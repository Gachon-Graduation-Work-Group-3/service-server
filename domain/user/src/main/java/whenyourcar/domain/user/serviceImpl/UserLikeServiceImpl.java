package whenyourcar.domain.user.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whenyourcar.domain.common.code.exception.GeneralException;
import whenyourcar.domain.common.code.status.ErrorStatus;
import whenyourcar.domain.user.converter.UserLikeConverter;
import whenyourcar.domain.user.dto.userLike.UserLikeResponse;
import whenyourcar.domain.user.service.UserLikeService;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.entity.User;
import whenyourcar.storage.mysql.data.mapping.UserLike;
import whenyourcar.storage.mysql.repository.car.CarCommonRepository;
import whenyourcar.storage.mysql.repository.car.CarSaleRepository;
import whenyourcar.storage.mysql.repository.user.UserCommonRepository;
import whenyourcar.storage.mysql.repository.user.UserLikeRepository;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final CarCommonRepository carCommonRepository;
    private final UserCommonRepository userCommonRepository;

    private final UserLikeConverter userLikeConverter;

    @Override
    public void postUserLike(Long carId, Long userId) {
        Car car = carCommonRepository.findCarByCarId(carId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.CAR_IS_NOT_EXIST));

        User user = userCommonRepository.findUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_IS_NOT_EXIST));

        UserLike userLike = userLikeConverter.toUserLike(car, user);
        userLikeRepository.save(userLike);
    }

    @Override
    public UserLikeResponse.UserLikesResponse getUserLikes(Long userId, Pageable pageable) {
        User user = userCommonRepository.findUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_IS_NOT_EXIST));

        return userLikeConverter.toUserLikes(userLikeRepository.findUserLikesByUser(pageable, userId), userId);
    }

    @Transactional
    @Override
    public void deleteUserLike(Long userId, Long userLikeId) {
        userLikeRepository.deleteUserLikeByIdAndUserId(userLikeId, userId);
    }
}
