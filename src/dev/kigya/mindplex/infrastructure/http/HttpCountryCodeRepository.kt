package dev.kigya.mindplex.infrastructure.http

import dev.kigya.mindplex.domain.model.CountryCodeEntity
import dev.kigya.mindplex.domain.port.ICountryCodeRepository
import dev.kigya.mindplex.infrastructure.http.mapper.toDomain
import dev.kigya.mindplex.infrastructure.http.model.HttpCountryCode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class HttpCountryCodeRepository(
    private val httpClient: HttpClient,
) : ICountryCodeRepository {
    override suspend fun fetchCountryCode(ip: String): CountryCodeEntity {
        val response: HttpCountryCode = httpClient
            .get("http://ip-api.com/json/$ip") { parameter("fields", "countryCode") }
            .body()
        return response.toDomain()
    }
}
