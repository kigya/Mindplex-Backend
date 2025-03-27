package dev.kigya.mindplex.adapters.outbound

import dev.kigya.mindplex.domain.model.Question
import dev.kigya.mindplex.domain.port.IQuestionProvider
import kotlinx.serialization.json.Json

class FileQuestionProvider : IQuestionProvider {
    override fun getAllQuestions(): List<Question> {
        val stream = {}.javaClass.getResourceAsStream("/data/questions.json")
            ?: error("Файл questions.json не найден в ресурсах")
        val content = stream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(content)
    }
}
