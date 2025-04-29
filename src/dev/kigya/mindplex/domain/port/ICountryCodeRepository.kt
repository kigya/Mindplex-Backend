package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.CountryCodeEntity

interface ICountryCodeRepository {
    suspend fun fetchCountryCode(ip: String): CountryCodeEntity
}