package com.uahannam.readmodel.repository

import com.uahannam.readmodel.entity.OrderItemJpaEntity
import com.uahannam.readmodel.entity.OrderItemJpaEntity2
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItemJpaEntity, Long>