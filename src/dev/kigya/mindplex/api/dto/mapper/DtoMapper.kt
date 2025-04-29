package dev.kigya.mindplex.api.dto.mapper

import dev.kigya.mindplex.api.dto.response.*
import dev.kigya.mindplex.domain.model.*

fun FactEntity.toResponse() =
    FactResponse(fact = fact)

fun QuestionEntity.toResponse() =
    QuestionResponse(
        type = type,
        difficulty = difficulty,
        category = category,
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = incorrectAnswers,
    )

fun UserProfileEntity.toResponse(): UserProfileResponse =
    UserProfileResponse(
        id = id,
        displayName = displayName,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
        globalRank = globalRank,
        localRank = localRank,
    )

fun NewScoreEntity.toResponse() =
    ScoreUpdateResponse(newScore = newScore)

fun UserEntity.toLeaderboardResponse() =
    LeaderboardUserResponse(
        id = id,
        displayName = name,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
    )

fun UserEntity.toGenerateDebugUserResponse(token: String) =
    GenerateDebugUserResponse(
        id = id,
        displayName = name,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
        token = token,
    )
