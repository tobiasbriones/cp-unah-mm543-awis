package com.mm543.awis.domain.model

data class Product(
    val productId: Int,
    val productModel: ProductModel,
    val name: String,
    val productNumber: Int,
    val makeFlag: Boolean,
    val finishedGoodsFlag: Boolean,
    val color: Int,
    val safetyStockLevel: Int,
    val standardCost: Double,
    val listPrice: Double
)
