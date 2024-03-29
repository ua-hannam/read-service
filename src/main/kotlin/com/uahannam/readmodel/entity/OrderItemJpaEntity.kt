package com.uahannam.readmodel.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "ORDER_ITEM")
class OrderItemJpaEntity(
    @Id
    @Column(name = "ORDER_ITEM_ID")
    val orderItemId: Long? = null,

    @Column(name = "ORDER_ID")
    val orderId: Long,

    @Column(name = "ITEM_ID")
    val itemId: Long,

    @Column(name = "ITEM_PRICE")
    val itemPrice: Int,

    @Column(name = "ITEM_NAME")
    val itemName: String,

    @Column(name = "ITEM_QUANTITY")
    val itemQuantity: Int,

    @Column(name = "ITEM_TOTAL_PRICE")
    val itemTotalPrice: Int,

    @Column(name = "REG_DATE")
    val regDate: LocalDateTime,

    @Column(name = "MOD_DATE")
    val modDate: LocalDateTime
)