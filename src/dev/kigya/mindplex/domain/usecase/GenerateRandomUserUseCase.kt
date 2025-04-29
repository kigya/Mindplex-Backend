package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.IRandomUserRepository
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class GenerateRandomUserUseCase(
    private val randomUserRepository: IRandomUserRepository,
    private val userRepository: IUserRepository,
) {
    suspend operator fun invoke(): UserEntity? {
        val randomUser: UserEntity? = randomUserRepository.generateRandomUser()
        return randomUser?.let {
            val userEntity = UserEntity(
                id = randomUser.id,
                name = randomUser.name,
                avatarUrl = randomUser.avatarUrl,
                countryCode = randomUser.countryCode,
                score = randomUser.score,
            )
            userRepository.save(InfrastructureStage.DEBUG, userEntity)
            randomUser
        }
    }
}
