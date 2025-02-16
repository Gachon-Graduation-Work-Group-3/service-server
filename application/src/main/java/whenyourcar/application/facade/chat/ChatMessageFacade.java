package whenyourcar.application.facade.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whenyourcar.application.dto.chat.message.ChatMessageResponse;
import whenyourcar.application.service.chat.ChatMessageService;
import whenyourcar.application.service.chat.ChatRoomService;
import whenyourcar.application.service.user.UserCommonService;
import whenyourcar.common.code.exception.GeneralException;
import whenyourcar.common.code.status.ErrorStatus;
import whenyourcar.domain.entity.User;

@Service
@RequiredArgsConstructor
public class ChatMessageFacade {
    private final UserCommonService userCommonService;
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    public ChatMessageResponse.ChatMessageResponseSetDto getChatMessage(String email,
                                                                        Long roomId,
                                                                        Pageable pageable) {
        User user = userCommonService.getUserByEmail(email);
        if (chatRoomService.isUserInRoom(roomId, user.getId())) {
            return chatMessageService.getChat(pageable, roomId, user.getId());
        }else {
            throw new GeneralException(ErrorStatus.ROOM_IS_NOT_EXIST);
        }
    }

}
