package dev.kigya.mindplex.domain.model

data class UserProfileEntity(
    val id: String,
    val displayName: String,
    val avatarUrl: String?,
    val countryCode: String?,
    val score: Int,
    val globalRank: Int,
    val localRank: Int?
)
