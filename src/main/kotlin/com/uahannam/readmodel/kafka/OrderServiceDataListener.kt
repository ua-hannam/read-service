package com.uahannam.readmodel.kafka

import com.uahannam.readmodel.domain.OrderKafkaDto
import com.uahannam.readmodel.service.SaveOrderDataUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class OrderServiceDataListener(
    private val saveOrderDataUseCase: SaveOrderDataUseCase
) {

    @KafkaListener(topics = ["save-order-data"], groupId = "save-order-data", containerFactory = "saveOrderServiceKafkaListenerContainerFactory")
    fun listenOrderData(@Payload order: OrderKafkaDto) {
        println("KAFKA 요청 도착 ============================================>")
        println("orderKafkaDTO : $order")
        saveOrderDataUseCase.saveOrderData(order)
    }
}