package dev.kigya.mindplex.adapters.inbound

import dev.kigya.mindplex.domain.service.QuestionService
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(questionService: QuestionService) {
    routing {
        rateLimit(RateLimitName(RateLimitKey.QUESTIONS)) {
            get("/questions") {
                val questions = questionService.getAllQuestions()
                call.respond(questions)
            }
        }
    }
}
