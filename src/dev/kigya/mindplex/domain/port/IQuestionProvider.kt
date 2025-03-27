package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.Question

interface IQuestionProvider {
    fun getAllQuestions(): List<Question>
}
