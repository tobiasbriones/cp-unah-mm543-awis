package com.mm543.awis.domain.model

data class PersonPhone(
    val businessEntity: BusinessEntity,
    val phoneNumberType: PhoneNumberType,
    val phoneNumber: String
)
