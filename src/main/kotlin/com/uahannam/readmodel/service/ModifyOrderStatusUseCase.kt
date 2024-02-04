package com.uahannam.readmodel.service

import com.uahannam.readmodel.dto.ModifyOrderStatusKafkaDto

interface ModifyOrderStatusUseCase {

    fun modifyOrderStatus(modifyStatusOrder: ModifyOrderStatusKafkaDto)
}