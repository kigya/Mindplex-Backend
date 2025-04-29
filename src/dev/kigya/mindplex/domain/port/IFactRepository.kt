package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.FactEntity

interface IFactRepository {
    suspend fun getFacts(limit: Int): List<FactEntity>
}
