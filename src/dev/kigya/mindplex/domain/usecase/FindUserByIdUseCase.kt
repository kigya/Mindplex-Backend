package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class FindUserByIdUseCase(
    private val userRepository: IUserRepository,
) {
    suspend operator fun invoke(stage: InfrastructureStage, id: String): UserEntity? =
        userRepository.findById(stage, id)
}
