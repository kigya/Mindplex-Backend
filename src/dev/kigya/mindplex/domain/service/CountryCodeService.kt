package dev.kigya.mindplex.domain.service

import dev.kigya.mindplex.domain.model.CountryCode
import dev.kigya.mindplex.domain.port.ICountryCodeProvider

class CountryCodeService(
    private val countryCodeProvider: ICountryCodeProvider,
) {
    suspend fun getCountryCode(ip: String): CountryCode = countryCodeProvider.getCountryCode(ip)
}