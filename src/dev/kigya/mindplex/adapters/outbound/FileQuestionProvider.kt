package dev.kigya.mindplex.adapters.outbound

import dev.kigya.mindplex.domain.model.Question
import dev.kigya.mindplex.domain.port.IQuestionProvider
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class FileQuestionProvider : IQuestionProvider {
    override fun getAllQuestions(): List<Question> {
        val stream = {}.javaClass.getResourceAsStream("/data/questions.json")
            ?: error("File questions.json not found in resources")
        val content = stream.bufferedReader().use(BufferedReader::readText)
        return Json.decodeFromString(content)
    }
}
