package com.mm543.awis.domain.model

import java.time.LocalDateTime

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

data class ProductModel(
    val productModelId: Int,
    val name: String,
    val catalogDescription: String,
    val instructions: String,
    val modifiedDate: LocalDateTime
)

data class ProductPhoto(
    val productPhotoId: Int,
    val thumbNailPhoto: String,
    val thumbnailPhotoFileName: String,
    val largePhotoFileName: String,
    val modifiedDate: LocalDateTime
)
