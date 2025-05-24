package dev.kigya.mindplex.main

import com.typesafe.config.ConfigFactory
import dev.kigya.mindplex.api.auth.configureAuthentication
import dev.kigya.mindplex.api.routing.configureRouting
import dev.kigya.mindplex.api.routing.rate.configureRateLimit
import dev.kigya.mindplex.di.appModule
import dev.kigya.mindplex.util.plugin.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.resources.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args = args)
}

fun Application.module() {
    val hocon = ConfigFactory
        .parseResources("application.conf")
        .withFallback(ConfigFactory.systemEnvironment())
        .resolve()

    val jwtSecret = hocon.getString("jwt.secret")
    val jwtIssuer = hocon.getString("jwt.issuer")

    install(Resources)
    install(Koin) {
        properties(
            mapOf(
                "jwt.secret" to jwtSecret,
                "jwt.issuer" to jwtIssuer
            )
        )

        slf4jLogger()
        modules(appModule)
    }

    configureFirebase()
    configureAuthentication()

    configureCORS()
    configureRateLimit()
    configureMonitoring()
    configureSerialization()
    configureStatusPages()
    configureMicrometer()
    configureRequestValidation()
    configureCompression()

    configureRouting()
}
