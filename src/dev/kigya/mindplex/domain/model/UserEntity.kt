package dev.kigya.mindplex.domain.model

data class UserEntity(
    val id: String,
    val name: String,
    val avatarUrl: String?,
    var countryCode: String?,
    var score: Int,
)
