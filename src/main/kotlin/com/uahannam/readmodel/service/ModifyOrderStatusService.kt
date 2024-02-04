package com.uahannam.readmodel.service

import com.uahannam.readmodel.dto.ModifyOrderStatusKafkaDto
import com.uahannam.readmodel.repository.OrderEventRepository
import com.uahannam.readmodel.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyOrderStatusService(
    private val orderRepository: OrderRepository,
    private val orderEventRepository: OrderEventRepository,
) : ModifyOrderStatusUseCase {

    override fun modifyOrderStatus(modifyStatusOrder: ModifyOrderStatusKafkaDto) {
        val findOrderEvent = orderEventRepository.findByEventUUID(modifyStatusOrder.orderEvent.eventUUID)

        if (findOrderEvent == null) {
            orderRepository.findById(modifyStatusOrder.modifyOrderStatusEventDto.orderId)
                .ifPresent {
                    orderRepository.modifyOrderStatus(
                        modifyStatusOrder.modifyOrderStatusEventDto.orderStatus,
                        modifyStatusOrder.modifyOrderStatusEventDto.orderId
                    )
                }
        }
    }
}