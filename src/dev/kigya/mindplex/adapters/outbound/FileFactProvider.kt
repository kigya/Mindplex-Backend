package dev.kigya.mindplex.adapters.outbound

import dev.kigya.mindplex.domain.model.Fact
import dev.kigya.mindplex.domain.port.IFactProvider
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class FileFactProvider : IFactProvider {
    override fun getFacts(limit: Int): List<Fact> {
        val stream = {}.javaClass.getResourceAsStream("/data/facts.json")
            ?: error("File facts.json not found in resources")
        val content = stream.bufferedReader().use(BufferedReader::readText)
        return Json.decodeFromString<List<Fact>>(content).shuffled().take(limit)
    }
}
