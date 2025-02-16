package whenyourcar.application.service.chat;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import whenyourcar.application.dto.chat.common.ChatCommonResponse;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchRoomInfoQuery;

public interface ChatRoomService {
    public SearchRoomInfoQuery getChatRoom(Long roomId, User user);
    public Page<ChatCommonResponse.ChatRoomResponseDto> getChatRooms(Pageable pageable, User user);
    public boolean isUserInRoom(Long roomId, Long userId);

    public void createChatRoom(User user1, User user2, CarSale carSale);
}
