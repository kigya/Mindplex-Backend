package dev.kigya.mindplex.api.routing.routes

import dev.kigya.mindplex.api.routing.resource.RootResource
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.rootRoute() {
    get<RootResource> {
        call.respondRedirect("swagger")
    }
}

fun Routing.swaggerRoute() {
    swaggerUI(
        path = "swagger",
        swaggerFile = "openapi/documentation.yaml"
    )
}
