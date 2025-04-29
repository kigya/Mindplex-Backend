package dev.kigya.mindplex.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    @SerialName("type")
    val type: String,

    @SerialName("difficulty")
    val difficulty: String,

    @SerialName("category")
    val category: String,

    @SerialName("question")
    val question: String,

    @SerialName("correctAnswer")
    val correctAnswer: String,

    @SerialName("incorrectAnswers")
    val incorrectAnswers: List<String>
)
