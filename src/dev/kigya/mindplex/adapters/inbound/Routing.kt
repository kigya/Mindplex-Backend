package dev.kigya.mindplex.adapters.inbound

import dev.kigya.mindplex.domain.service.QuestionService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(questionService: QuestionService) {
    routing {
        get("/") {
            call.respondRedirect("swagger")
        }

        swaggerUI(
            path = "swagger",
            swaggerFile = "openapi/documentation.yaml"
        )

        rateLimit(RateLimitName(RateLimitKey.QUESTIONS)) {
            get("/questions") {
                call.response.headers.append(HttpHeaders.CacheControl, "public, max-age=36000")
                val questions = questionService.getAllQuestions()
                call.respond(questions)
            }
        }
    }
}
