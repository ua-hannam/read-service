package com.uahannam.readmodel.repository

import com.uahannam.readmodel.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderJpaEntity, Long> {
}