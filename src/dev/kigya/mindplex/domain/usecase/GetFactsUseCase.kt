package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.FactEntity
import dev.kigya.mindplex.domain.port.IFactRepository

class GetFactsUseCase(
    private val factRepository: IFactRepository,
) {
    suspend operator fun invoke(limit: Int?): List<FactEntity> {
        return factRepository.getFacts(limit ?: FACT_LIMIT)
    }

    private companion object {
        const val FACT_LIMIT = 3
    }
}