package dev.kigya.mindplex.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    @SerialName("id") val id: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("avatarUrl") val avatarUrl: String?,
    @SerialName("countryCode") val countryCode: String?,
    @SerialName("score") val score: Int,
    @SerialName("globalRank") val globalRank: Int,
    @SerialName("localRank") val localRank: Int?
)
