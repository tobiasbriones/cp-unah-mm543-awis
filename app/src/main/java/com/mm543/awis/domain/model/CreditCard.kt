package com.mm543.awis.domain.model

import java.time.LocalDateTime

data class CreditCard(
    val creditCardId: Int,
    val cardType: String,
    val cardNumber: String,
    val expMonth: String,
    val expYear: String,
    val modifiedDate: LocalDateTime
)
