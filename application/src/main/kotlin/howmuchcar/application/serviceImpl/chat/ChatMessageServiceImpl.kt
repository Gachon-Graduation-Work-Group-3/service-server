package howmuchcar.application.serviceImpl.chat

import howmuchcar.application.converter.chat.ChatMessageConverter
import howmuchcar.application.dto.chat.ChatMessage
import howmuchcar.application.dto.chat.ChatMessageResponse
import howmuchcar.application.service.chat.ChatMessageService
import howmuchcar.application.status.ChatErrorStatus
import howmuchcar.application.status.UserErrorStatus
import howmuchcar.common.exception.RestApiException
import howmuchcar.domain.entity.Chat
import howmuchcar.domain.entity.Room
import howmuchcar.domain.entity.User
import howmuchcar.domain.query.chat.SearchChatsQuery
import howmuchcar.domain.query.chat.SearchRoomUsersQuery
import howmuchcar.domain.repository.chat.ChatRepository
import howmuchcar.domain.repository.chat.ChatRoomRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.amqp.core.*
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class ChatMessageServiceImpl(
    private val rabbitAdmin: RabbitAdmin,
    private val rabbitTemplate: RabbitTemplate,
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRepository: ChatRepository,
    private val chatMessageConverter: ChatMessageConverter
): ChatMessageService {

    @Value("\${rabbitmq.chat-exchange.name}")
    private val CHAT_EXCHANGE_NAME: String? = null

    @Value("\${rabbitmq.chat-queue.name}")
    private val CHAT_QUEUE_NAME: String? = null

    override fun getChat(pageable: Pageable, roomId: Long, userId: Long): ChatMessageResponse {
        val chats: Page<SearchChatsQuery> = chatRepository.findChatByRoomId(pageable, roomId)
        val sender = chatRoomRepository.findOtherUser(roomId, userId)
            .orElseThrow{RestApiException(UserErrorStatus.NOT_EXIST_USER)}
        return chatMessageConverter.toGetMessageDto(chats, roomId, sender)
    }

    @Transactional
    override fun saveChat(roomId: Long, sender: User, message: String): String {
        val room: Room = chatRoomRepository.findRoomById(roomId)
            .orElseThrow { RestApiException(ChatErrorStatus.NOT_EXIST_ROOM) }

        val chat: Chat = chatRepository.save(chatMessageConverter.toChat(room, sender, message))
        return chat.id
    }

    override fun createMessageQueueForUser(userId: Long) {
        val queueName = CHAT_QUEUE_NAME + userId
        val userQueue = Queue(queueName, true)
        rabbitAdmin.declareQueue(userQueue)

        val exchange = TopicExchange(CHAT_EXCHANGE_NAME)
        val binding = BindingBuilder
            .bind(userQueue)
            .to(exchange)
            .with(queueName)
        rabbitAdmin.declareBinding(binding)
    }

    @Transactional
    override fun sendMessageToMessageQueue(roomId: Long, chatId: String, senderId: Long, message: String) {
        val searchRoomUsersQuery: SearchRoomUsersQuery = chatRoomRepository.findUsersByRoomId(roomId)
        val chatMessage: ChatMessage =
            chatMessageConverter.toChatMessage(chatId, senderId, roomId, message)

        val queueName1 = CHAT_QUEUE_NAME + searchRoomUsersQuery.user1Id
        val queueName2 = CHAT_QUEUE_NAME + searchRoomUsersQuery.user2Id
        println("SEND TO QUEUE : $queueName1")
        println("SEND TO QUEUE : $queueName2")

        val messageProperties = MessageProperties()
        messageProperties.timestamp = Date() // 현재 시간을 타임스탬프로 설정

        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, queueName1, chatMessage) { message: Message ->
            message.messageProperties.timestamp = Date()
            message
        }

        rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, queueName2, chatMessage) { message: Message ->
            message.messageProperties.timestamp = Date()
            message
        }
    }


}