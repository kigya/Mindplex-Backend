package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.UserProfileEntity
import dev.kigya.mindplex.domain.port.ICountryCodeRepository
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.util.InfrastructureStage

class GetUserProfileUseCase(
    private val userRepository: IUserRepository,
    private val countryCodeProvider: ICountryCodeRepository,
) {
    suspend operator fun invoke(
        stage: InfrastructureStage,
        id: String,
        ip: String?
    ): UserProfileEntity {
        val user = userRepository.findById(stage, id)
            ?: throw NoSuchElementException("User $id not found")

        if (user.countryCode.isNullOrBlank() && ip != null) {
            val cc = try {
                countryCodeProvider.fetchCountryCode(ip).countryCode
            } catch (_: Exception) {
                user.countryCode
            }
            user.countryCode = cc
            userRepository.save(stage, user)
        }

        val all = userRepository.findAllOrderedByScore(stage)
        val globalRank = all.indexOfFirst { it.id == id } + 1
        val localList = all.filter { it.countryCode == user.countryCode }
        val localRank = localList.indexOfFirst { it.id == id } + 1

        return UserProfileEntity(
            id = user.id,
            displayName = user.name,
            avatarUrl = user.avatarUrl,
            countryCode = user.countryCode,
            score = user.score,
            globalRank = globalRank,
            localRank = localRank.takeIf { it > 0 }
        )
    }
}