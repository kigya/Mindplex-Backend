package dev.kigya.mindplex.main

import dev.kigya.mindplex.adapters.inbound.configureMonitoring
import dev.kigya.mindplex.adapters.inbound.configureRateLimit
import dev.kigya.mindplex.adapters.inbound.configureRouting
import dev.kigya.mindplex.adapters.inbound.configureSerialization
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
    configureRouting(questionService = MindplexApplication.questionService)
}
