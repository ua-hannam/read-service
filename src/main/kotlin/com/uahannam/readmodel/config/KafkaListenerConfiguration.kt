package com.uahannam.readmodel.config

import com.uahannam.readmodel.dto.ModifyOrderStatusKafkaDto
import com.uahannam.readmodel.dto.OrderKafkaDto
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

    @Bean(name = ["modifyOrderStatusKafkaListenerContainerFactory"])
    fun modifyOrderStatusKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, ModifyOrderStatusKafkaDto> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, ModifyOrderStatusKafkaDto>()
        concurrentKafkaListenerContainerFactory.consumerFactory = modifyOrderStatusConsumerFactory()

        return concurrentKafkaListenerContainerFactory
    }

    @Bean(name = ["modifyOrderStatusConsumerFactory"])
    fun modifyOrderStatusConsumerFactory() : ConsumerFactory<String, ModifyOrderStatusKafkaDto> {
        val deserializer = JsonDeserializer(ModifyOrderStatusKafkaDto::class.java, false)


        val modifyOrderConsumerConfigurations = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to environment["kafka.uri"],
            ConsumerConfig.GROUP_ID_CONFIG to environment["modify-order-status.group-id"],
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest"
        )

        return DefaultKafkaConsumerFactory(modifyOrderConsumerConfigurations, StringDeserializer(), deserializer)
    }

    @Bean(name = ["saveOrderServiceKafkaListenerContainerFactory"])
    fun saveOrderServiceKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto>()
        concurrentKafkaListenerContainerFactory.consumerFactory = saveOrderServiceConsumerFactory()

        return concurrentKafkaListenerContainerFactory
    }

    @Bean(name = ["saveOrderServiceConsumerFactory"])
    fun saveOrderServiceConsumerFactory() : ConsumerFactory<String, OrderKafkaDto> {
        val deserializer = JsonDeserializer(OrderKafkaDto::class.java, false)


        val saveOrderConsumerConfigurations = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to environment["kafka.uri"],
            ConsumerConfig.GROUP_ID_CONFIG to environment["save-order-service.group-id"],
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest"
        )

        return DefaultKafkaConsumerFactory(saveOrderConsumerConfigurations, StringDeserializer(), deserializer)
    }
}