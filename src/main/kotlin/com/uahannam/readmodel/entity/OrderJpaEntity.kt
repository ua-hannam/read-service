package com.uahannam.readmodel.entity

import com.uahannam.readmodel.domain.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "ORDERS")
class OrderJpaEntity(
    @Id
    @Column(name = "ORDER_ID")
    val orderId: Long? = null,

    @Column(name = "MEMBER_ID")
    val memberId: Long,

    @Column(name = "ADDRESS")
    val address: String,

    @Column(name = "STORE_ID")
    val storeId: Long,

    @Column(name = "TOTAL_PRICE")
    val totalPrice: Int,

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    @Column(name = "REG_DATE")
    val regDate: LocalDateTime,

    @Column(name = "MOD_DATE")
    val modDate: LocalDateTime

    ) {
    fun updateOrderStatus(orderStatus: OrderStatus) {
        this.orderStatus = orderStatus
    }
}