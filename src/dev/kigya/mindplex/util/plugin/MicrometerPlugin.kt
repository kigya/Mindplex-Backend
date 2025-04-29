package dev.kigya.mindplex.util.plugin

import dev.kigya.mindplex.api.routing.resource.MetricsResource
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry

fun Application.configureMicrometer() {
    val prometheusRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    install(MicrometerMetrics) {
        registry = prometheusRegistry
    }

    routing {
        get<MetricsResource> {
            call.respond(prometheusRegistry.scrape())
        }
    }
}
