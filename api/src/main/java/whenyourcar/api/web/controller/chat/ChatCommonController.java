package whenyourcar.api.web.controller.chat;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.chat.common.ChatCommonResponse;
import whenyourcar.application.facade.chat.ChatCommonFacade;
import whenyourcar.common.code.status.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/room")
public class ChatCommonController {
    private final ChatCommonFacade chatCommonFacade;
    @PostMapping("/")
    public ApiResponse<Void> postChatRoom(
            HttpServletRequest httpServletRequest,
            @RequestParam Long user2Id,
            @RequestParam Long carId)  {
        String email = httpServletRequest.getHeader("X-User-Email");
        chatCommonFacade.postChatRoom(user2Id, email, carId);
        return ApiResponse.onSuccess(SuccessStatus.CHAT_ROOM_CREATE_SUCCESS, null);
    }

    @GetMapping("/info")
    public ApiResponse<ChatCommonResponse.ChatRoomInfoResponseDto> getChatRoomInfo(
            HttpServletRequest httpServletRequest,
            @RequestParam Long roomId
    ) {
        String email = httpServletRequest.getHeader("X-User-Email");
        return ApiResponse.onSuccess(SuccessStatus.CHAT_SEARCH_ROOM_SUCCESS, chatCommonFacade.getChatRoomInfo(roomId, email));
    }

    @GetMapping("/")
    public ApiResponse<Page<ChatCommonResponse.ChatRoomResponseDto>> getChatRooms(
            HttpServletRequest httpServletRequest,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        String email = httpServletRequest.getHeader("X-User-Email");
        return ApiResponse.onSuccess(SuccessStatus.CHAT_SEARCH_ROOM_SUCCESS, chatCommonFacade.getChatRooms( PageRequest.of(page, size), email));
    }
}
