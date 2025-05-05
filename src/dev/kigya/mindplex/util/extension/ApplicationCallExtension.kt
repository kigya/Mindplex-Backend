package dev.kigya.mindplex.util.extension

import dev.kigya.mindplex.util.InfrastructureStage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

val ApplicationCall.stage: InfrastructureStage
    get() = request.headers[HttpHeaders.XStage]?.let {
        if (it.equals("debug", true)) {
            InfrastructureStage.DEBUG
        } else
            InfrastructureStage.RELEASE
    } ?: InfrastructureStage.DEBUG

val ApplicationCall.userId: String
    get() =
        principal<JWTPrincipal>()!!.payload.subject

