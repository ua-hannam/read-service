package com.uahannam.readmodel.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime

@Entity(name = "ORDERS")
data class OrderJpaEntity(

    @Id
    @Column(name = "ORDER_ID")
    val orderId: Long,

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

    @Column(name = "DEL_STATUS")
    val delStatus: Boolean,

    @Column(name = "ORDER_REG_DATE")
    val regDate: LocalDateTime,

    @Column(name = "ORDER_MOD_DATE")
    val modDate: LocalDateTime

    ) : ReadModelBaseEntity(), Persistable<Long> {
    fun updateOrderStatus(orderStatus: OrderStatus) {
        this.orderStatus = orderStatus
    }

    override fun getId() = orderId

    override fun isNew() = readModelRegDate == null
}