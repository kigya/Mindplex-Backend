package dev.kigya.mindplex.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryCode(
    @SerialName("countryCode") val countryCode: String,
)
