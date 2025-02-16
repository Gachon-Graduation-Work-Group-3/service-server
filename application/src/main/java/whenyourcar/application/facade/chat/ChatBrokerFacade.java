package whenyourcar.application.facade.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whenyourcar.application.service.chat.ChatMessageService;

@Service
@RequiredArgsConstructor
public class ChatBrokerFacade {
    private final ChatMessageService chatMessageService;

    public void sendMessageToQueue(Long roomId,
                                   Long sender,
                                   String message) {
        String chatId = chatMessageService.saveChat(roomId, sender, message);
        chatMessageService.sendMessageToMessageQueue(roomId, chatId, sender, message);
    }


}
