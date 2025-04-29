package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.NewScoreEntity
import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.util.InfrastructureStage

interface IUserRepository {
    suspend fun save(stage: InfrastructureStage, userEntity: UserEntity)
    suspend fun findById(stage: InfrastructureStage, id: String): UserEntity?
    suspend fun incrementScore(stage: InfrastructureStage, id: String, delta: Int): NewScoreEntity
    suspend fun findTopByScore(stage: InfrastructureStage, limit: Int): List<UserEntity>
    suspend fun findAllOrderedByScore(stage: InfrastructureStage): List<UserEntity>
}
