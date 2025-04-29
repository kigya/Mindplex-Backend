package dev.kigya.mindplex.infrastructure.firestore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirestoreNewScore(
    @SerialName("score") val newScore: Int,
)
