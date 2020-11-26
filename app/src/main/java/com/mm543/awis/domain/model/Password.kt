package com.mm543.awis.domain.model

data class Password(
    val businessEntity: BusinessEntity,
    val passwordHash: String,
    val passwordSalt: String
)
