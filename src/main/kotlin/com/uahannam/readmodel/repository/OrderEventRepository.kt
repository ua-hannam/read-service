package com.uahannam.readmodel.repository

import com.uahannam.readmodel.entity.OrderEventJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderEventRepository : JpaRepository<OrderEventJpaEntity, Long> {
    fun findByEventUUID(uuid: String): OrderEventJpaEntity?
}
