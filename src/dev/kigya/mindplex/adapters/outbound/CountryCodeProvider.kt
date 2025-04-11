package dev.kigya.mindplex.adapters.outbound

import dev.kigya.mindplex.adapters.outbound.exception.OutboundAdapterException
import dev.kigya.mindplex.domain.model.CountryCode
import dev.kigya.mindplex.domain.port.ICountryCodeProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CountryCodeProvider(
    private val httpClient: HttpClient
) : ICountryCodeProvider {

    override suspend fun getCountryCode(ip: String): CountryCode {
        try {
            val response = httpClient.get("http://ip-api.com/json/$ip") {
                parameter("fields", "countryCode")
            }
            val country = response.body<CountryCode>()
            return CountryCode(country.countryCode)
        } catch (e: Exception) {
            throw OutboundAdapterException.CountryCodeNotFoundException(
                message = "Unable to retrieve country code for IP: $ip",
            )
        }
    }
}
