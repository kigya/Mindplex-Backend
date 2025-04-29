package dev.kigya.mindplex.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateDebugUserResponse(
    @SerialName("id") val id: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("avatarUrl") val avatarUrl: String?,
    @SerialName("countryCode") val countryCode: String?,
    @SerialName("score") val score: Int,
    @SerialName("mindplexToken") val token: String
)
