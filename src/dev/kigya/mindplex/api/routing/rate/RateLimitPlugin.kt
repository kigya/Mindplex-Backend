package dev.kigya.mindplex.api.routing.rate

import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.Duration.Companion.minutes
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
        register(RateLimitName(RateLimitKey.USER)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
        register(RateLimitName(RateLimitKey.USER_PROFILE)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
        register(RateLimitName(RateLimitKey.USER_SCORE)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
        register(RateLimitName(RateLimitKey.LEADERBOARD)) {
            rateLimiter(
                limit = 64,
                refillPeriod = 1.seconds,
            )
        }
        register(RateLimitName(RateLimitKey.USER_GENERATE_DEBUG)) {
            rateLimiter(
                limit = 2,
                refillPeriod = 1.minutes,
            )
        }
    }
}

object RateLimitKey {
    const val QUESTIONS = "questionsLimit"
    const val FACTS = "factsLimit"

    const val USER = "userLimit"
    const val USER_PROFILE = "userProfile"
    const val USER_SCORE = "userScore"
    const val USER_GENERATE_DEBUG = "userGenerateDebug"

    const val LEADERBOARD = "leaderboardLimit"
}
