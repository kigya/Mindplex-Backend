package dev.kigya.mindplex.di

import dev.kigya.mindplex.adapters.outbound.CountryCodeProvider
import dev.kigya.mindplex.adapters.outbound.FileFactProvider
import dev.kigya.mindplex.adapters.outbound.FileQuestionProvider
import dev.kigya.mindplex.domain.port.ICountryCodeProvider
import dev.kigya.mindplex.domain.port.IFactProvider
import dev.kigya.mindplex.domain.port.IQuestionProvider
import dev.kigya.mindplex.domain.service.CountryCodeService
import dev.kigya.mindplex.domain.service.FactService
import dev.kigya.mindplex.domain.service.QuestionService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
            }
        }
    }

    single<IFactProvider> { FileFactProvider() }
    single<IQuestionProvider> { FileQuestionProvider() }
    single<ICountryCodeProvider> { CountryCodeProvider(httpClient = get()) }

    single { FactService(factProvider = get()) }
    single { QuestionService(questionProvider = get()) }
    single { CountryCodeService(countryCodeProvider = get()) }
}
