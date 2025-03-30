package howmuchcar.common.handler

import howmuchcar.common.auth.JwtProvider
import howmuchcar.common.code.status.AuthErrorStatus
import howmuchcar.common.exception.RestApiException
import lombok.RequiredArgsConstructor
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
@RequiredArgsConstructor
class StompHandler(
    private val jwtProvider: JwtProvider
) : ChannelInterceptor{
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = StompHeaderAccessor.wrap(message)
        if(accessor.command == null) return message

        when(accessor.command) {
            StompCommand.CONNECT -> {
                authorizationToken(accessor)
                println("Connect")
            }
            StompCommand.DISCONNECT -> {
                println("Stop Listening")
            }
            StompCommand.SEND -> {
                authorizationToken(accessor)
            }
            else -> {
                println(accessor.command)
                println("Destination: ${accessor.destination}")
                println("User: ${accessor.user}")
            }
        }
        return message
    }

    override fun afterSendCompletion(message: Message<*>, channel: MessageChannel, sent: Boolean, ex: Exception?) {
        val accessor = StompHeaderAccessor.wrap(message)
        if(accessor.command == null) return

        when(accessor.command) {
            StompCommand.SUBSCRIBE -> {
                println("STOMP Listening Start")
            }
            else -> {}
        }

    }

    private fun authorizationToken(accessor: StompHeaderAccessor) {
        val header: String = accessor.getFirstNativeHeader("Authorization")
            ?: throw RestApiException(AuthErrorStatus.INVALID_ACCESS_TOKEN)
        val accessToken = jwtProvider.resolveToken(header)
            ?: throw RestApiException(AuthErrorStatus.INVALID_ACCESS_TOKEN)
        if(!jwtProvider.validateAccessToken(accessToken)) {
            throw RestApiException(AuthErrorStatus.INVALID_ACCESS_TOKEN)
        }
    }
}