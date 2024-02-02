package com.uahannam.readmodel.config

import com.uahannam.readmodel.domain.OrderKafkaDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaListenerConfiguration(
    private val environment: Environment
) {

    @Bean(name = ["saveOrderServiceKafkaListenerContainerFactory"])
    fun saveOrderServiceKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto>()
        concurrentKafkaListenerContainerFactory.consumerFactory = saveOrderServiceConsumerFactory()

        return concurrentKafkaListenerContainerFactory
    }

    @Bean(name = ["saveOrderServiceConsumerFactory"])
    fun saveOrderServiceConsumerFactory() : ConsumerFactory<String, OrderKafkaDto> {
        val deserializer = JsonDeserializer(OrderKafkaDto::class.java, false)


        val consumerConfigurations = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to environment["kafka.uri"],
            ConsumerConfig.GROUP_ID_CONFIG to environment["save-order-service.group-id"],
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest"
        )

        return DefaultKafkaConsumerFactory(consumerConfigurations, StringDeserializer(), deserializer)
    }
}