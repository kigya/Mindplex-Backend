package dev.kigya.mindplex.infrastructure.file

import dev.kigya.mindplex.domain.model.QuestionEntity
import dev.kigya.mindplex.domain.port.IQuestionRepository
import dev.kigya.mindplex.infrastructure.file.mapper.toDomain
import dev.kigya.mindplex.infrastructure.file.model.FileQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class FileQuestionRepository : IQuestionRepository {
    override suspend fun getAllQuestions(): List<QuestionEntity> =
        withContext(Dispatchers.IO) {
            val stream = {}.javaClass.getResourceAsStream("/data/questions.json")
                ?: error("File questions.json not found in resources")
            val content = stream.bufferedReader().use(BufferedReader::readText)
            Json
                .decodeFromString<List<FileQuestion>>(content)
                .map(FileQuestion::toDomain)
        }
}