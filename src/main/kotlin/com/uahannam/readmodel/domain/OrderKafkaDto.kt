package com.uahannam.readmodel.domain

import com.uahannam.readmodel.entity.OrderEventJpaEntity
import com.uahannam.readmodel.entity.OrderItemJpaEntity
import com.uahannam.readmodel.entity.OrderJpaEntity
import java.time.LocalDateTime

data class OrderKafkaDto(
    val order: Order,
    val orderItem: List<OrderItem>,
    val orderEvent: OrderEvent
) {
    fun mapToOrderJpaEntity() =
        OrderJpaEntity(
            orderId = order.orderId,
            memberId = order.memberId,
            address = order.address,
            storeId = order.storeId,
            totalPrice = order.totalPrice,
            orderStatus = order.orderStatus,
            regDate = order.regDate,
            modDate = order.modDate
        )

    fun mapToOrderItemEntityList() =
        orderItem.map {
            orderItem -> OrderItemJpaEntity(
                orderItemId = orderItem.orderItemId,
                orderId = orderItem.orderId,
                itemId = orderItem.itemId,
                itemPrice = orderItem.itemPrice,
                itemName = orderItem.itemName,
                itemQuantity = orderItem.itemQuantity,
                itemTotalPrice = orderItem.itemTotalPrice,
                regDate = orderItem.regDate,
                modDate = orderItem.modDate
            )
        }.toList()

    fun mapToOrderEventJpaEntity() =
        OrderEventJpaEntity(
            eventUUID = orderEvent.eventUUID,
            orderId = orderEvent.orderId
        )

}

data class Order(
    val orderId: Long,
    val memberId: Long,
    val address: String,
    val storeId: Long,
    val totalPrice: Int,
    val orderStatus: OrderStatus,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)

data class OrderItem(
    val orderItemId: Long,
    val orderId: Long,
    val itemId: Long,
    val itemPrice: Int,
    val itemName: String,
    val itemQuantity: Int,
    val itemTotalPrice: Int,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)

data class OrderEvent(
    val eventUUID: String,
    val orderId: Long
)