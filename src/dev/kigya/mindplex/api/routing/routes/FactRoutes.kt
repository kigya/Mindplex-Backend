package dev.kigya.mindplex.api.routing.routes

import dev.kigya.mindplex.api.auth.JWT_MINDPLEX_AUTH_KEY
import dev.kigya.mindplex.api.dto.mapper.toResponse
import dev.kigya.mindplex.api.routing.rate.RateLimitKey
import dev.kigya.mindplex.api.routing.resource.FactsResource
import dev.kigya.mindplex.domain.model.FactEntity
import dev.kigya.mindplex.domain.usecase.GetFactsUseCase
import io.ktor.server.auth.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.factRoutes(getFactsUseCase: GetFactsUseCase) {
    rateLimit(RateLimitName(RateLimitKey.FACTS)) {
        authenticate(JWT_MINDPLEX_AUTH_KEY) {
            get<FactsResource> {
                val limit = call.parameters["limit"]?.toIntOrNull()
                call.respond(getFactsUseCase.invoke(limit).map(FactEntity::toResponse))
            }
        }
    }
}
