package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.QuestionEntity

interface IQuestionRepository {
    suspend fun getAllQuestions(): List<QuestionEntity>
}
