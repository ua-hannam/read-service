package com.uahannam.readmodel.dto

import com.uahannam.readmodel.entity.OrderStatus

data class ModifyOrderStatusKafkaDto(
    val modifyOrderStatusEventDto: ModifyOrderStatusEventDto,
    val orderEvent: OrderEvent,
)

data class ModifyOrderStatusEventDto(
    val orderId: Long,
    val orderStatus: OrderStatus
)