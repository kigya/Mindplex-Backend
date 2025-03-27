package dev.kigya.mindplex.adapters.inbound

import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.Duration.Companion.seconds

fun Application.configureRateLimit() {
    install(RateLimit) {
        register(RateLimitName(RateLimitKey.QUESTIONS)) {
            rateLimiter(
                limit = 1,
                refillPeriod = 5.seconds,
            )
        }
    }
}

object RateLimitKey {
    const val QUESTIONS = "questionsLimit"
}
