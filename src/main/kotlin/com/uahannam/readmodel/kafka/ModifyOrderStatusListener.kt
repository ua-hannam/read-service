package com.uahannam.readmodel.kafka

import com.uahannam.readmodel.dto.ModifyOrderStatusEventDto
import com.uahannam.readmodel.dto.ModifyOrderStatusKafkaDto
import com.uahannam.readmodel.service.ModifyOrderStatusUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ModifyOrderStatusListener(
    private val modifyOrderStatusUseCase: ModifyOrderStatusUseCase
) {

    @KafkaListener(topics = ["modify-order-status"], groupId = "modify-order-status", containerFactory = "modifyOrderStatusKafkaListenerContainerFactory")
    fun listenOrderData(@Payload modifyOrderStatusKafkaDto: ModifyOrderStatusKafkaDto) {
        modifyOrderStatusUseCase.modifyOrderStatus(modifyOrderStatusKafkaDto)
    }
}