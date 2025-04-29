package dev.kigya.mindplex.api.routing

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import dev.kigya.mindplex.api.auth.JwtService
import dev.kigya.mindplex.api.routing.routes.*
import dev.kigya.mindplex.domain.usecase.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val upsertUserUseCase: UpsertUserUseCase by inject()
    val getUserProfileUseCase: GetUserProfileUseCase by inject()
    val incrementScoreUseCase: IncrementScoreUseCase by inject()
    val getFactsUseCase: GetFactsUseCase by inject()
    val getQuestionsUseCase: GetQuestionsUseCase by inject()
    val getLeaderboardUseCase: GetLeaderboardUseCase by inject()
    val generateRandomUserUseCase: GenerateRandomUserUseCase by inject()
    val jwtService: JwtService by inject()
    val googleVerifier: GoogleIdTokenVerifier by inject()

    routing {
        rootRoute()
        swaggerRoute()

        userRoutes(
            jwtService,
            googleVerifier,
            upsertUserUseCase,
            getUserProfileUseCase,
            incrementScoreUseCase,
            generateRandomUserUseCase,
        )
        factRoutes(getFactsUseCase)
        questionRoutes(getQuestionsUseCase)
        leaderboardRoutes(getLeaderboardUseCase)
    }
}
