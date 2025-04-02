package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.Fact

interface IFactProvider {
    fun getFacts(limit: Int): List<Fact>
}
