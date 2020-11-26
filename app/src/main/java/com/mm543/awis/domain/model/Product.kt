/*
 * AWIS: Adventure Works Information Systems, Android App
 * Created for the MM543-0900-3-2020 course.
 * Team: Tobias Briones, Juan Varela
 *
 * Copyright (c) 2020 Tobias Briones.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

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
