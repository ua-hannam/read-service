package com.uahannam.readmodel.service

import com.uahannam.readmodel.domain.OrderKafkaDto

interface SaveOrderDataUseCase {

    fun saveOrderData(order: OrderKafkaDto)
}