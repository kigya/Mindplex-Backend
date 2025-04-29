package dev.kigya.mindplex.api.routing.routes

import dev.kigya.mindplex.api.auth.JWT_MINDPLEX_AUTH_KEY
import dev.kigya.mindplex.api.dto.mapper.toResponse
import dev.kigya.mindplex.api.routing.rate.RateLimitKey
import dev.kigya.mindplex.api.routing.resource.QuestionsResource
import dev.kigya.mindplex.domain.model.QuestionEntity
import dev.kigya.mindplex.domain.usecase.GetQuestionsUseCase
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.questionRoutes(getQuestionsUseCase: GetQuestionsUseCase) {
    rateLimit(RateLimitName(RateLimitKey.QUESTIONS)) {
        authenticate(JWT_MINDPLEX_AUTH_KEY) {
            get<QuestionsResource> {
                call.response.headers.append(HttpHeaders.CacheControl, "public, max-age=36000")
                call.respond(getQuestionsUseCase().map(QuestionEntity::toResponse))
            }
        }
    }
}
