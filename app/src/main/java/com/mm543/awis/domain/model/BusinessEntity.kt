package com.mm543.awis.domain.model

import java.time.LocalDateTime

data class BusinessEntity(
    val businessEntityId: Int,
    val modifiedDate: LocalDateTime
)

data class BusinessEntityContact(
    val businessEntity: BusinessEntity,
    val person: Person
)
