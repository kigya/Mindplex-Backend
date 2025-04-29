package dev.kigya.mindplex.infrastructure.http

import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.port.IRandomUserRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

class HttpRandomUserRepository(
    private val httpClient: HttpClient,
) : IRandomUserRepository {

    @Serializable
    private data class ApiResponse(
        val results: List<Result>
    ) {
        @Serializable
        data class Result(
            val name: Name,
            val picture: Picture,
            @SerialName("nat") val countryCode: String
        ) {
            @Serializable
            data class Name(val first: String, val last: String)

            @Serializable
            data class Picture(val large: String)
        }
    }

    override suspend fun generateRandomUser(): UserEntity? {
        val api: ApiResponse = httpClient
            .get("https://randomuser.me/api/?inc=name,picture,nat&noinfo")
            .body()

        val result = api.results.firstOrNull() ?: return null

        val id = (1..21)
            .joinToString("") { Random.nextInt(0, 10).toString() }

        val displayName = "${result.name.first} ${result.name.last}"
        val avatarUrl = result.picture.large
        val countryCode = result.countryCode
        val score = Random.nextInt(0, 1001)

        return UserEntity(
            id = id,
            name = displayName,
            avatarUrl = avatarUrl,
            countryCode = countryCode,
            score = score
        )
    }
}