package dev.kigya.mindplex.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreUpdateResponse(
    @SerialName("newScore") val newScore: Int
)
