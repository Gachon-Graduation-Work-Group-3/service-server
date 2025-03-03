package whenyourcar.api.web.controller.chat;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whenyourcar.api.apiPayload.ApiResponse;
import whenyourcar.application.dto.chat.message.ChatMessageResponse;
import whenyourcar.application.facade.chat.ChatMessageFacade;
import whenyourcar.common.code.status.SuccessStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/message")
public class ChatMessageController {
    private final ChatMessageFacade chatMessageFacade;
    @GetMapping("/")
    public ApiResponse<ChatMessageResponse.ChatMessageResponseSetDto> getChatMessage(
            HttpServletRequest httpServletRequest,
            @RequestParam Long roomId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        String email = httpServletRequest.getHeader("X-User-Email");
        return ApiResponse.onSuccess(SuccessStatus.CHAT_SEARCH_MESSAGE_SUCCESS, chatMessageFacade.getChatMessage(email, roomId, PageRequest.of(page, size)));
    }
}
