package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.NewScoreEntity
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class IncrementScoreUseCase(
    private val userRepository: IUserRepository,
) {
    suspend operator fun invoke(
        stage: InfrastructureStage,
        userId: String,
        delta: Int,
    ): NewScoreEntity =
        userRepository.incrementScore(
            stage = stage,
            id = userId,
            delta = delta,
        )
}