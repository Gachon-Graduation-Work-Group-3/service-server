package whenyourcar.application.facade.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.converter.chat.ChatCommonConverter;
import whenyourcar.application.dto.chat.common.ChatCommonResponse;
import whenyourcar.application.service.car.CarSaleService;
import whenyourcar.application.service.chat.ChatMessageService;
import whenyourcar.application.service.chat.ChatRoomService;
import whenyourcar.application.service.user.UserCommonService;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchRoomInfoQuery;

@Service
@RequiredArgsConstructor
public class ChatCommonFacade {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserCommonService userCommonService;
    private final CarSaleService carSaleService;

    private final ChatCommonConverter chatCommonConverter;
    public void postChatRoom(Long userId, String email, Long carId) {
        User user1 = userCommonService.getUserByEmail(email);
        User user2 = userCommonService.getUserById(userId);

        CarSale carSale = carSaleService.findCarSaleById(carId);

        chatRoomService.createChatRoom(user1, user2, carSale);

        chatMessageService.createMessageQueueForUser(user1.getId());
        chatMessageService.createMessageQueueForUser(user2.getId());
    }

    public Page<ChatCommonResponse.ChatRoomResponseDto> getChatRooms(Pageable pageable,
                                                                     String email) {
        User user = userCommonService.getUserByEmail(email);
        return chatRoomService.getChatRooms(pageable, user);
    }

    public ChatCommonResponse.ChatRoomInfoResponseDto getChatRoomInfo(Long roomId,
                                                                            String email) {
        User user = userCommonService.getUserByEmail(email);
        SearchRoomInfoQuery searchRoomInfoQuery = chatRoomService.getChatRoom(roomId, user);
        return chatCommonConverter.toChatRoomResponse(searchRoomInfoQuery);
    }
}
