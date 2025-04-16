package dev.kigya.mindplex.adapters.inbound.route

import io.ktor.resources.*

@Resource("/")
class Root

@Resource("/metrics")
class Metrics

@Resource("/questions")
class Questions

@Resource("/facts")
class Facts(val limit: Int? = 3)

@Resource("/country")
class Country
