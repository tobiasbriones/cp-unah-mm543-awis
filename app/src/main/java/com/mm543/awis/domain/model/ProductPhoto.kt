package com.mm543.awis.domain.model

import java.time.LocalDateTime

data class ProductPhoto(
    val productPhotoId: Int,
    val thumbNailPhoto: String,
    val thumbnailPhotoFileName: String,
    val largePhotoFileName: String,
    val modifiedDate: LocalDateTime
)
