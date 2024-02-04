package com.uahannam.readmodel.service

import com.uahannam.readmodel.dto.OrderKafkaDto
import com.uahannam.readmodel.repository.OrderEventRepository
import com.uahannam.readmodel.repository.OrderItemRepository
import com.uahannam.readmodel.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SaveOrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val orderEventRepository: OrderEventRepository,
) : SaveOrderDataUseCase {

    override fun saveOrderData(saveEventOrder: OrderKafkaDto) {
        val findOrderEvent = orderEventRepository.findByEventUUID(saveEventOrder.orderEvent.eventUUID)

        if (findOrderEvent == null) {
            orderRepository.save(saveEventOrder.mapToOrderJpaEntity())
            orderItemRepository.saveAll(saveEventOrder.mapToOrderItemEntityList())
            orderEventRepository.save(saveEventOrder.mapToOrderEventJpaEntity())
        }
    }
}