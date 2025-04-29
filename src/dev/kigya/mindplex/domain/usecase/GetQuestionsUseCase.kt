package dev.kigya.mindplex.domain.usecase

import dev.kigya.mindplex.domain.model.QuestionEntity
import dev.kigya.mindplex.domain.port.IQuestionRepository

class GetQuestionsUseCase(
    private val questionRepository: IQuestionRepository,
) {
    suspend operator fun invoke(): List<QuestionEntity> {
        return questionRepository.getAllQuestions()
    }
}