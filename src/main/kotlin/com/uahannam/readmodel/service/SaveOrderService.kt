package com.uahannam.readmodel.service

import com.uahannam.readmodel.domain.OrderKafkaDto
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

    override fun saveOrderData(order: OrderKafkaDto) {
        val findOrderEvent = orderEventRepository.findByEventUUID(order.orderEvent.eventUUID)

        if (findOrderEvent == null) {
            orderRepository.save(order.mapToOrderJpaEntity())
            orderItemRepository.saveAll(order.mapToOrderItemEntityList())
            orderEventRepository.save(order.mapToOrderEventJpaEntity())
        }
    }
}