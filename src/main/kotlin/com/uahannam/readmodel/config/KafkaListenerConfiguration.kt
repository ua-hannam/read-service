package com.uahannam.readmodel.config

import com.uahannam.readmodel.domain.Order
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
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaListenerConfiguration(
    private val environment: Environment
) {

    @Bean
    fun saveOrderServiceKafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto> {
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, OrderKafkaDto>()
        concurrentKafkaListenerContainerFactory.consumerFactory = saveOrderServiceConsumerFactory()

        return concurrentKafkaListenerContainerFactory
    }

    @Bean
    fun saveOrderServiceConsumerFactory() : ConsumerFactory<String, OrderKafkaDto> {
        val jacksonTypeMapper = DefaultJackson2JavaTypeMapper()
        jacksonTypeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.INFERRED
        jacksonTypeMapper.addTrustedPackages("*")

        val deserializer = JsonDeserializer(OrderKafkaDto::class.java)
        deserializer.typeMapper = jacksonTypeMapper
        deserializer.addTrustedPackages("*")

        val consumerConfigurations = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "",
            ConsumerConfig.GROUP_ID_CONFIG to environment["order-service.save-order-data"],
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest"
        )

        return DefaultKafkaConsumerFactory(consumerConfigurations, StringDeserializer(), deserializer)
    }
}