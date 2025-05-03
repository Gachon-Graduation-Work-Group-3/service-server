package howmuchcar.api.controller.chat

import howmuchcar.api.security.annotation.CurrentUser
import howmuchcar.application.dto.chat.ChatRoomCarInfoResponse
import howmuchcar.application.dto.chat.ChatRoomInfoResponse
import howmuchcar.application.facade.chat.ChatCommonFacade
import howmuchcar.common.apiPayload.ApiResponse
import howmuchcar.domain.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat/room")
class ChatCommonController(
    private val chatCommonFacade: ChatCommonFacade
) {
    @PostMapping("/")
    fun postChatRoom(
        @CurrentUser user: User,
        @RequestParam user2Id: Long,
        @RequestParam carId: Long
    ): ApiResponse<Void> {
        chatCommonFacade.postChatRoom(user, user2Id, carId)
        return ApiResponse.onSuccess(null)
    }

    @GetMapping("info")
    fun getChatRoomCarInfo(@CurrentUser user: User,
                        roomId: Long
    ): ApiResponse<ChatRoomCarInfoResponse> {
        return ApiResponse.onSuccess(chatCommonFacade.getChatRoomCarInfo(roomId, user))
    }

    @GetMapping("/")
    fun getChatRooms(@CurrentUser user: User,
                     @RequestParam page: Int,
                     @RequestParam size: Int
    ) : ApiResponse<Page<ChatRoomInfoResponse>> {
        return ApiResponse.onSuccess(chatCommonFacade.getChatRoom( PageRequest.of(page, size), user))
    }
}