package dev.kigya.mindplex.util.extension

import dev.kigya.mindplex.util.InfrastructureStage
import io.ktor.http.*
import io.ktor.server.application.*

fun ApplicationCall.stage(): InfrastructureStage =
    request.headers[HttpHeaders.XStage]?.let {
        if (it.equals("debug", true)) {
            InfrastructureStage.DEBUG
        } else
            InfrastructureStage.RELEASE
    } ?: InfrastructureStage.DEBUG
