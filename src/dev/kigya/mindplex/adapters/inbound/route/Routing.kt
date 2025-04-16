package dev.kigya.mindplex.adapters.inbound.route

import dev.kigya.mindplex.adapters.extension.Delimiters
import dev.kigya.mindplex.adapters.extension.XRealIP
import dev.kigya.mindplex.adapters.inbound.RateLimitKey
import dev.kigya.mindplex.domain.service.CountryCodeService
import dev.kigya.mindplex.domain.service.FactService
import dev.kigya.mindplex.domain.service.QuestionService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val questionService by inject<QuestionService>()
    val factService by inject<FactService>()
    val countryCodeService by inject<CountryCodeService>()

    routing {
        rootRoute()
        swaggerRoute()
        questionsRoute(questionService)
        factsRoute(factService)
        countryRoute(countryCodeService)
    }
}

private fun Routing.rootRoute() {
    get<Root> {
        call.respondRedirect("swagger")
    }
}

private fun Routing.swaggerRoute() {
    swaggerUI(
        path = "swagger",
        swaggerFile = "openapi/documentation.yaml"
    )
}

private fun Routing.questionsRoute(questionService: QuestionService) {
    rateLimit(RateLimitName(RateLimitKey.QUESTIONS)) {
        get<Questions> {
            call.response.headers.append(HttpHeaders.CacheControl, "public, max-age=36000")
            val questions = questionService.getAllQuestions()
            call.respond(questions)
        }
    }
}

private fun Routing.factsRoute(factService: FactService) {
    rateLimit(RateLimitName(RateLimitKey.FACTS)) {
        get<Facts> {
            call.respond(factService.getFacts(limit = it.limit))
        }
    }
}

private fun Routing.countryRoute(countryCodeService: CountryCodeService) {
    rateLimit(RateLimitName(RateLimitKey.COUNTRY)) {
        get<Country> {
            val ip = call.request.headers[HttpHeaders.XForwardedFor]
                ?.split(Delimiters.COMMA)
                ?.firstOrNull()
                ?.trim()
                ?: call.request.headers[HttpHeaders.XRealIP]
                ?: call.request.origin.remoteHost

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
