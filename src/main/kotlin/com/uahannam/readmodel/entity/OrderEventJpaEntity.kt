package com.uahannam.readmodel.entity

import jakarta.persistence.*

@Entity(name = "ORDER_EVENT")
class OrderEventJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_EVENT_ID")
    val orderEventId: Long? = null,

    @Column(name = "EVENT_UUID")
    val eventUUID: String,

    @Column(name = "ORDER_ID")
    val orderId: Long,
 ) : DateBaseEntity()