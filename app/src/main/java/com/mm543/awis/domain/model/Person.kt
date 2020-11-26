package com.mm543.awis.domain.model

data class Person(
    val businessEntity: BusinessEntity,
    val personType: String,
    val nameStyle: String,
    val title: String,
    val firstName: String,
    val middleName: String,
    val lastName: String
)
