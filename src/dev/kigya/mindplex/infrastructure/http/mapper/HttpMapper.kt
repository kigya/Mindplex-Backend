package dev.kigya.mindplex.infrastructure.http.mapper

import dev.kigya.mindplex.domain.model.CountryCodeEntity
import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.infrastructure.http.model.HttpCountryCode
import dev.kigya.mindplex.infrastructure.http.model.HttpRandomUser

fun HttpCountryCode.toDomain(): CountryCodeEntity =
    CountryCodeEntity(countryCode = countryCode)

fun HttpRandomUser.toDomain(): UserEntity =
    UserEntity(
        id = id,
        name = displayName,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
    )
