package com.uahannam.readmodel.service

import com.uahannam.readmodel.dto.OrderKafkaDto

interface SaveOrderDataUseCase {

    fun saveOrderData(saveEventOrder: OrderKafkaDto)
}