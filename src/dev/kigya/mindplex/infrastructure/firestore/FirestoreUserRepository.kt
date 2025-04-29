package dev.kigya.mindplex.infrastructure.firestore

import com.google.cloud.firestore.FieldValue
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.Query
import com.google.cloud.firestore.SetOptions
import dev.kigya.mindplex.domain.model.NewScoreEntity
import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.IUserRepository
import dev.kigya.mindplex.infrastructure.firestore.mapper.toDomain
import dev.kigya.mindplex.infrastructure.firestore.mapper.toFirestore
import dev.kigya.mindplex.infrastructure.firestore.model.FirestoreNewScore
import dev.kigya.mindplex.infrastructure.firestore.model.FirestoreUser
import dev.kigya.mindplex.util.InfrastructureStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirestoreUserRepository(
    private val firestore: Firestore,
) : IUserRepository {

    override suspend fun save(
        stage: InfrastructureStage,
        userEntity: UserEntity,
    ): Unit = withContext(Dispatchers.IO) {
        val docRef = firestore
            .collection(stage.code)
            .document(userEntity.id)

        val snapshot = docRef.get().get()
        if (!snapshot.exists()) {
            docRef
                .set(userEntity.toFirestore())
                .get()
        } else {
            val updates = mapOf<String, Any?>(
                "name" to userEntity.name,
                "avatarUrl" to userEntity.avatarUrl,
                "countryCode" to userEntity.countryCode
            )
            docRef
                .set(updates, SetOptions.merge())
                .get()
        }
    }

    override suspend fun findById(
        stage: InfrastructureStage,
        id: String,
    ): UserEntity? = withContext(Dispatchers.IO) {
        firestore
            .collection(stage.code)
            .document(id)
            .get().get()
            .toObject(FirestoreUser::class.java)?.toDomain()
    }

    override suspend fun incrementScore(
        stage: InfrastructureStage,
        id: String,
        delta: Int,
    ): NewScoreEntity =
        withContext(Dispatchers.IO) {
            firestore
                .collection(stage.code)
                .document(id)
                .update("score", FieldValue.increment(delta.toLong()))
                .get()

            FirestoreNewScore(findById(stage, id)!!.score).toDomain()
        }

    override suspend fun findTopByScore(
        stage: InfrastructureStage,
        limit: Int,
    ): List<UserEntity> =
        withContext(Dispatchers.IO) {
            firestore
                .collection(stage.code)
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(limit)
                .get().get()
                .toObjects(FirestoreUser::class.java)
                .map(FirestoreUser::toDomain)
        }

    override suspend fun findAllOrderedByScore(stage: InfrastructureStage): List<UserEntity> =
        withContext(Dispatchers.IO) {
            firestore
                .collection(stage.code)
                .orderBy("score", Query.Direction.DESCENDING)
                .get().get()
                .toObjects(FirestoreUser::class.java)
                .map(FirestoreUser::toDomain)
        }
}
