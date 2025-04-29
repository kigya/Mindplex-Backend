package dev.kigya.mindplex.api.routing.routes

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import dev.kigya.mindplex.api.auth.JWT_MINDPLEX_AUTH_KEY
import dev.kigya.mindplex.api.auth.JwtService
import dev.kigya.mindplex.api.dto.mapper.toGenerateDebugUserResponse
import dev.kigya.mindplex.api.dto.mapper.toResponse
import dev.kigya.mindplex.api.dto.request.ScoreUpdateRequest
import dev.kigya.mindplex.api.dto.request.UserPostRequest
import dev.kigya.mindplex.api.dto.response.AuthResponse
import dev.kigya.mindplex.api.routing.rate.RateLimitKey
import dev.kigya.mindplex.api.routing.resource.UserResource
import dev.kigya.mindplex.domain.usecase.GenerateRandomUserUseCase
import dev.kigya.mindplex.domain.usecase.GetUserProfileUseCase
import dev.kigya.mindplex.domain.usecase.IncrementScoreUseCase
import dev.kigya.mindplex.domain.usecase.UpsertUserUseCase
import dev.kigya.mindplex.util.extension.callIp
import dev.kigya.mindplex.util.extension.stage
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.patch
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(
    jwtService: JwtService,
    googleVerifier: GoogleIdTokenVerifier,
    upsertUserUseCase: UpsertUserUseCase,
    getUserProfileUseCase: GetUserProfileUseCase,
    incrementScoreUseCase: IncrementScoreUseCase,
    generateRandomUserUseCase: GenerateRandomUserUseCase,
) {
    rateLimit(RateLimitName(RateLimitKey.USER)) {
        post<UserResource> {
            val dto = call.receive<UserPostRequest>()

            val idToken = googleVerifier.verify(dto.token)
            if (idToken == null) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid Google ID token")
                return@post
            }

            val googleUid = idToken.payload.subject

            val env = call.stage()
            upsertUserUseCase(
                stage = env,
                id = googleUid,
                displayName = dto.displayName,
                avatarUrl = dto.avatarUrl,
                ip = callIp,
            )

            val mindplexToken = jwtService.generateToken(googleUid)
            call.respond(AuthResponse(token = mindplexToken))
        }
    }

    rateLimit(RateLimitName(RateLimitKey.USER_PROFILE)) {
        authenticate(JWT_MINDPLEX_AUTH_KEY) {
            get<UserResource.ProfileResource> {
                val principal = call.principal<JWTPrincipal>()!!
                val uid = principal.payload.subject
                val env = call.stage()
                val profile = getUserProfileUseCase(env, uid, callIp).toResponse()
                call.respond(profile)
            }
        }
    }

    rateLimit(RateLimitName(RateLimitKey.USER_SCORE)) {
        authenticate(JWT_MINDPLEX_AUTH_KEY) {
            patch<UserResource.ScoreResource> {
                val principal = call.principal<JWTPrincipal>()!!
                val uid = principal.payload.subject
                val delta = call.receive<ScoreUpdateRequest>().delta
                val env = call.stage()
                val newScore = incrementScoreUseCase(env, uid, delta)
                call.respond(newScore.toResponse())
            }
        }
    }

    rateLimit(RateLimitName(RateLimitKey.USER_GENERATE_DEBUG)) {
        get<UserResource.GenerateDebugResource> {
            val generatedUser = generateRandomUserUseCase()

            generatedUser?.let {
                val token = jwtService.generateToken(generatedUser.id)

                call.respond(
                    HttpStatusCode.Created,
                    generatedUser.toGenerateDebugUserResponse(token)
                )
            } ?: call.respond(HttpStatusCode.ServiceUnavailable)
        }
    }
}
