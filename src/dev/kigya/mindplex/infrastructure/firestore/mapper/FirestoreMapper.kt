package dev.kigya.mindplex.infrastructure.firestore.mapper

import dev.kigya.mindplex.domain.model.NewScoreEntity
import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.infrastructure.firestore.model.FirestoreNewScore
import dev.kigya.mindplex.infrastructure.firestore.model.FirestoreUser

fun FirestoreUser.toDomain(): UserEntity =
    UserEntity(
        id = id,
        name = name,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
    )

fun UserEntity.toFirestore(): FirestoreUser =
    FirestoreUser(
        id = id,
        name = name,
        avatarUrl = avatarUrl,
        countryCode = countryCode,
        score = score,
    )

fun FirestoreNewScore.toDomain() =
    NewScoreEntity(newScore = newScore)
