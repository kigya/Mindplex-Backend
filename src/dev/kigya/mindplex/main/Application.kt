package dev.kigya.mindplex.main

import dev.kigya.mindplex.adapters.inbound.*
import dev.kigya.mindplex.di.appModule
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.forwardedheaders.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(modules = listOf(appModule))
    }

    install(ForwardedHeaders)

    configureSerialization()
    configureMonitoring()
    configureRateLimit()
    configureCORS()
    configureRouting()
}
