package howmuchcar.common.config

import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
class RabbitMQConfig {
    @Value("\${rabbitmq.chat-exchange.name}")
    lateinit var chatExchangeName: String

    @Value("\${spring.data.rabbitmq.host}")
    lateinit var rabbitMQHost: String

    @Bean
    fun chatExchange(): TopicExchange {return TopicExchange(chatExchangeName)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter:MessageConverter
    ):RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun rabbitMessagingTemplate(rabbitTemplate: RabbitTemplate): RabbitMessagingTemplate {
        return RabbitMessagingTemplate(rabbitTemplate)
    }

    @Bean
    fun simpleRabbitListenerContainerFactory(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMessageConverter(messageConverter)
        return factory
    }

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
        val admin = RabbitAdmin(connectionFactory)
        admin.isAutoStartup = true
        return admin
    }

    @Bean
    fun rabbitMQConnectionFactory(): ConnectionFactory {
        val factory = CachingConnectionFactory()
        factory.host = rabbitMQHost
        factory.port = 5672
        factory.username = "guest"
        factory.setPassword("guest")
        factory.virtualHost = "/"
        return factory
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}