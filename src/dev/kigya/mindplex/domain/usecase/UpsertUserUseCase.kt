package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.ICountryCodeRepository
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class UpsertUserUseCase(
    private val userRepository: IUserRepository,
    private val countryCodeProvider: ICountryCodeRepository
) {
    suspend operator fun invoke(
        stage: InfrastructureStage,
        id: String,
        displayName: String,
        avatarUrl: String?,
        ip: String
    ) {
        val countryCode = try {
            countryCodeProvider.fetchCountryCode(ip).countryCode
        } catch (_: Exception) {
            null
        }
        val userEntity = UserEntity(
            id = id,
            name = displayName,
            avatarUrl = avatarUrl,
            countryCode = countryCode,
            score = 0
        )
        userRepository.save(stage, userEntity)
    }
}
