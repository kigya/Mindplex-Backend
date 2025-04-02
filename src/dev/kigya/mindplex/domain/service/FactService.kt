package dev.kigya.mindplex.domain.service

import dev.kigya.mindplex.domain.model.Fact
import dev.kigya.mindplex.domain.port.IFactProvider

private const val DEFAULT_FACT_LIMIT = 3

class FactService(
    private val factProvider: IFactProvider,
) {
    fun getFacts(limit: Int? = null): List<Fact> = factProvider.getFacts(
        limit = limit ?: DEFAULT_FACT_LIMIT,
    )
}
