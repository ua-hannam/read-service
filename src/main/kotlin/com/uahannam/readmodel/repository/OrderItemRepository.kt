package com.uahannam.readmodel.repository

import com.uahannam.readmodel.entity.OrderItemJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItemJpaEntity, Long>