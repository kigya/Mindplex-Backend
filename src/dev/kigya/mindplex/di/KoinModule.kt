package dev.kigya.mindplex.di

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.firebase.cloud.FirestoreClient
import dev.kigya.mindplex.api.auth.JwtService
import dev.kigya.mindplex.domain.port.*
import dev.kigya.mindplex.domain.usecase.*
import dev.kigya.mindplex.infrastructure.file.FileFactRepository
import dev.kigya.mindplex.infrastructure.file.FileQuestionRepository
import dev.kigya.mindplex.infrastructure.firestore.FirestoreUserRepository
import dev.kigya.mindplex.infrastructure.http.HttpCountryCodeRepository
import dev.kigya.mindplex.infrastructure.http.HttpRandomUserRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true;
                        isLenient = true;
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                header(HttpHeaders.UserAgent, "Mozilla/5.0")
            }
        }
    }

    single { FirestoreClient.getFirestore() }

    single {
        val secret = getProperty<String>("jwt.secret")
        val issuer = getProperty<String>("jwt.issuer")
        JwtService(secret, issuer)
    }

    single {
        GoogleIdTokenVerifier.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance()
        )
            .setAudience(listOf("415983434678-aid9os1bmjts7vrqup66au7slsacp4hu.apps.googleusercontent.com"))
            .build()
    }

    single { FileFactRepository() } bind IFactRepository::class
    single { FileQuestionRepository() } bind IQuestionRepository::class
    single { HttpCountryCodeRepository(get()) } bind ICountryCodeRepository::class
    single { FirestoreUserRepository(get()) } bind IUserRepository::class
    single { HttpRandomUserRepository(get()) } bind IRandomUserRepository::class

    single { GetFactsUseCase(get()) }
    single { GetQuestionsUseCase(get()) }
    single { UpsertUserUseCase(get(), get()) }
    single { GetUserProfileUseCase(get(), get()) }
    single { IncrementScoreUseCase(get()) }
    single { GetLeaderboardUseCase(get()) }
    single { FindUserByIdUseCase(get()) }
    single { GenerateRandomUserUseCase(get(), get()) }
}
