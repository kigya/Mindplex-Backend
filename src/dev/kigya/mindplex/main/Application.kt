package dev.kigya.mindplex.main

import dev.kigya.mindplex.adapters.inbound.*
import dev.kigya.mindplex.application.MindplexApplication
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRateLimit()
    configureCORS()
    configureRouting(
        questionService = MindplexApplication.questionService,
        factService = MindplexApplication.factService,
    )
}
