package dev.kigya.mindplex.infrastructure.file.mapper

import dev.kigya.mindplex.domain.model.FactEntity
import dev.kigya.mindplex.domain.model.QuestionEntity
import dev.kigya.mindplex.infrastructure.file.model.FileFact
import dev.kigya.mindplex.infrastructure.file.model.FileQuestion

fun FileFact.toDomain(): FactEntity =
    FactEntity(fact = fact)

fun FileQuestion.toDomain() =
    QuestionEntity(
        type = type,
        difficulty = difficulty,
        category = category,
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers,
    )