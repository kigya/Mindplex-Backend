package dev.kigya.mindplex.domain.service

import dev.kigya.mindplex.domain.model.Question
import dev.kigya.mindplex.domain.port.IQuestionProvider

class QuestionService(
    private val questionProvider: IQuestionProvider,
) {
    fun getAllQuestions(): List<Question> = questionProvider.getAllQuestions()
}
