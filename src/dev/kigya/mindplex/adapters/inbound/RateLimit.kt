package dev.kigya.mindplex.adapters.inbound

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.Duration.Companion.seconds

fun Application.configureRateLimit() {
    install(RateLimit) {
        register(RateLimitName(RateLimitKey.QUESTIONS)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
        register(RateLimitName(RateLimitKey.FACTS)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
    }
}

object RateLimitKey {
    const val QUESTIONS = "questionsLimit"
    const val FACTS = "factsLimit"
}
