package dev.kigya.mindplex.infrastructure.file

import dev.kigya.mindplex.domain.model.FactEntity
import dev.kigya.mindplex.domain.port.IFactRepository
import dev.kigya.mindplex.infrastructure.file.mapper.toDomain
import dev.kigya.mindplex.infrastructure.file.model.FileFact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class FileFactRepository : IFactRepository {
    override suspend fun getFacts(limit: Int): List<FactEntity> =
        withContext(Dispatchers.IO) {
            val stream = {}.javaClass.getResourceAsStream("/data/facts.json")
                ?: error("File facts.json not found in resources")
            val content = stream.bufferedReader().use(BufferedReader::readText)
            Json
                .decodeFromString<List<FileFact>>(content)
                .shuffled()
                .take(limit)
                .map(FileFact::toDomain)
        }
}
