package dev.kigya.mindplex.util.plugin

import dev.kigya.mindplex.util.extension.XRealIP
import dev.kigya.mindplex.util.extension.XStage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCORS() {
    install(CORS) {
        allowHost("mindplex-backend.onrender.com", schemes = listOf("https"))
        allowHost("localhost:8080", schemes = listOf("http"))

        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)

        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.XStage)
        allowHeader(HttpHeaders.XRealIP)
    }
}
