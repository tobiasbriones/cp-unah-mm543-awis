package com.mm543.awis.domain.model

import java.time.LocalDateTime

data class ProductModel(
    val productModelId: Int,
    val name: String,
    val catalogDescription: String,
    val instructions: String,
    val modifiedDate: LocalDateTime
)
