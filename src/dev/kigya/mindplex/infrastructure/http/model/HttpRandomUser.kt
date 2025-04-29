package dev.kigya.mindplex.infrastructure.http.model

data class HttpRandomUser(
    val id: String,
    val displayName: String,
    val avatarUrl: String,
    val countryCode: String,
    val score: Int,
)
