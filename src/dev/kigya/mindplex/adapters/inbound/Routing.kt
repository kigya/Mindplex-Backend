package dev.kigya.mindplex.adapters.inbound

import dev.kigya.mindplex.domain.service.CountryCodeService
import dev.kigya.mindplex.domain.service.FactService
import dev.kigya.mindplex.domain.service.QuestionService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val questionService by inject<QuestionService>()
    val factService by inject<FactService>()
    val countryCodeService by inject<CountryCodeService>()

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

        rateLimit(RateLimitName(RateLimitKey.FACTS)) {
            get("/facts") {
                val limitParam = call.request.queryParameters["limit"]?.toIntOrNull()
                val facts = factService.getFacts(limitParam)
                call.respond(facts)
            }
        }

        rateLimit(RateLimitName(RateLimitKey.COUNTRY)) {
            get("/country") {
                val ip = call.request.origin.remoteHost
                try {
                    val countryCode = countryCodeService.getCountryCode(ip)
                    call.respond(countryCode)
                } catch (e: Exception) {
                    call.respond(
                        status = HttpStatusCode.BadGateway,
                        message = "Failed to get country code. ${e.message}",
                    )
                }
            }
        }
    }
}
