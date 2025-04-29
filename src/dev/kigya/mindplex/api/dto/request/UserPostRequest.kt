package dev.kigya.mindplex.api.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPostRequest(
    @SerialName("token") val token: String,
    @SerialName("displayName") val displayName: String,
    @SerialName("avatarUrl") val avatarUrl: String? = null
)
