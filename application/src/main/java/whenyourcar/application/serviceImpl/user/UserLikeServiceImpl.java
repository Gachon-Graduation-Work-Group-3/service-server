package whenyourcar.application.serviceImpl.user;

import whenyourcar.domain.entity.Car;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.entity.UserLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whenyourcar.domain.repository.user.UserCommonRepository;
import whenyourcar.domain.repository.user.UserLikeRepository;
import whenyourcar.application.converter.user.UserLikeConverter;
import whenyourcar.application.dto.user.like.UserLikeResponse;
import whenyourcar.application.service.user.UserLikeService;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final UserCommonRepository userCommonRepository;

    private final UserLikeConverter userLikeConverter;

    @Override
    public void postUserLike(Car car, User user) {
        UserLike userLike = userLikeConverter.toUserLike(car, user);
        userLikeRepository.save(userLike);
    }

    @Override
    public UserLikeResponse.UserLikesResponseDto getUserLikes(User user, Pageable pageable) {
        return userLikeConverter.toUserLikes(userLikeRepository.findUserLikesByUser(pageable, user.getId()), user.getId());
    }

    @Transactional
    @Override
    public void deleteUserLike(String email, Long userLikeId) {
        userLikeRepository.deleteUserLikeByIdAndUserEmail(userLikeId, email);
    }
}
