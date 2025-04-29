package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.UserEntity

interface IRandomUserRepository {
    suspend fun generateRandomUser(): UserEntity?
}
