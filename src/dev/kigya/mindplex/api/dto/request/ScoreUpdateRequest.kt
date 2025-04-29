package dev.kigya.mindplex.api.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreUpdateRequest(
    @SerialName("delta") val delta: Int
)
