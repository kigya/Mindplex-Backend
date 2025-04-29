package dev.kigya.mindplex.domain.model

data class QuestionEntity(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
