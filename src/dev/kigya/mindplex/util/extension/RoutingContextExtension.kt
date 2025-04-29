package dev.kigya.mindplex.util.extension

import dev.kigya.mindplex.util.Delimiters
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*

val RoutingContext.callIp: String
    get() = call.request.headers[HttpHeaders.XForwardedFor]
        ?.split(Delimiters.COMMA)
        ?.firstOrNull()
        ?.trim()
        ?: call.request.headers[HttpHeaders.XRealIP]
        ?: call.request.origin.remoteHost

fun RoutingContext.getUserId(): String? {
    val principal = call.principal<UserIdPrincipal>()
    return principal?.name
}
