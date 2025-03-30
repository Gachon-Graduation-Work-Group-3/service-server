package howmuchcar.api.controller.chat


import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.application.facade.chat.ChatMessageFacade
import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.common.auth.CurrentUser
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/message")
class ChatMessageController(
    private val chatMessageFacade: ChatMessageFacade
) {
    @GetMapping("/")
    fun getChatMessage(
        @CurrentUser user: User,
        @RequestParam roomId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int
    ) : ApiResponse<ChatMessageResponse> {
        return ApiResponse.onSuccess(chatMessageFacade.getChatMessage(user, roomId,PageRequest.of(page, size) ))
    }
}