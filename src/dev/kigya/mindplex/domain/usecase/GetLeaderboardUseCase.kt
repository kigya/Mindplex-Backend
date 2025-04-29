package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class GetLeaderboardUseCase(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(
        stage: InfrastructureStage,
        limit: Int? = DEFAULT_LIMIT
    ): List<UserEntity> =
        userRepository.findTopByScore(stage, limit ?: DEFAULT_LIMIT)

    private companion object {
        const val DEFAULT_LIMIT = 10
    }
}