package dev.kigya.mindplex.api.routing.routes

import dev.kigya.mindplex.api.auth.JWT_MINDPLEX_AUTH_KEY
import dev.kigya.mindplex.api.dto.mapper.toLeaderboardResponse
import dev.kigya.mindplex.api.routing.rate.RateLimitKey
import dev.kigya.mindplex.api.routing.resource.LeaderboardResource
import dev.kigya.mindplex.domain.model.UserEntity
import dev.kigya.mindplex.domain.usecase.GetLeaderboardUseCase
import dev.kigya.mindplex.util.extension.stage
import io.ktor.server.auth.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.leaderboardRoutes(getLeaderboardUseCase: GetLeaderboardUseCase) {
    rateLimit(RateLimitName(RateLimitKey.LEADERBOARD)) {
        authenticate(JWT_MINDPLEX_AUTH_KEY) {
            get<LeaderboardResource> { resource ->
                val env = call.stage()
                val limit = resource.limit
                val users = getLeaderboardUseCase(env, limit)
                    .map(UserEntity::toLeaderboardResponse)
                call.respond(users)
            }
        }
    }
}
