package dev.kigya.mindplex.domain.port

import dev.kigya.mindplex.domain.model.CountryCode

interface ICountryCodeProvider {
    suspend fun getCountryCode(ip: String): CountryCode
}
