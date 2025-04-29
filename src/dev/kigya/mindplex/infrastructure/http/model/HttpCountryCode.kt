package dev.kigya.mindplex.infrastructure.http.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HttpCountryCode(
    @SerialName("countryCode") val countryCode: String,
)
