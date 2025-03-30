package howmuchcar.common.config

import howmuchcar.common.handler.HttpSessionHandshakeInterceptor
import howmuchcar.common.handler.StompHandler
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
class StompConfig (
    private val httpSessionHandshakeInterceptor: HttpSessionHandshakeInterceptor,
    private val stompHandler: StompHandler
): WebSocketMessageBrokerConfigurer {

    @Value("\${spring.data.rabbitmq.host}")
    lateinit var rabbitMQHost: String

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("http://localhost:3000")
                .addInterceptors(httpSessionHandshakeInterceptor)
                .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/pub")
            .enableStompBrokerRelay("/topic", "/queue", "/exchange")
            .setRelayHost(rabbitMQHost)
            .setVirtualHost("/")
            .setRelayPort(61613)
            .setSystemLogin("guest")
            .setSystemPasscode("guest")
            .setClientLogin("guest")
            .setClientPasscode("guest")
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(stompHandler)
    }

}