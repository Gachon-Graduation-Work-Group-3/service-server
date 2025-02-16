package whenyourcar.application.serviceImpl.chat;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.converter.chat.ChatRoomConverter;
import whenyourcar.application.dto.chat.common.ChatCommonResponse;
import whenyourcar.application.service.chat.ChatRoomService;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchRoomInfoQuery;
import whenyourcar.domain.repository.chat.ChatRoomRepository;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomConverter chatRoomConverter;

    @Override
    public SearchRoomInfoQuery getChatRoom(Long roomId, User user) {
        return chatRoomRepository.findRoomByUserIdAndCarId(roomId, user.getId());
    }

    @Override
    public Page<ChatCommonResponse.ChatRoomResponseDto> getChatRooms(Pageable pageable, User user) {
        return chatRoomConverter.toChatCommonResponse(chatRoomRepository.findPageRoomsByUserId(pageable, user.getId()));
    }

    @Override
    public boolean isUserInRoom(Long roomId, Long userId) {
        return chatRoomRepository.existsRoomByUserId(roomId, userId);
    }

    @Transactional
    @Override
    public void createChatRoom(User user1, User user2, CarSale carSale) {
        if(!chatRoomRepository.existsRoomByUsersAndCarId(user1.getId(), user2.getId(), carSale.getCarId())) {
            chatRoomRepository.save(chatRoomConverter.toChatRoom(user1, user2, carSale));
        }
    }
}
