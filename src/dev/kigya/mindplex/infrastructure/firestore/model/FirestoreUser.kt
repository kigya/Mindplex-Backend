package dev.kigya.mindplex.infrastructure.firestore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirestoreUser(
    @SerialName("id") val id: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("avatarUrl") val avatarUrl: String? = null,
    @SerialName("countryCode") val countryCode: String? = null,
    @SerialName("score") val score: Int = 0
)
