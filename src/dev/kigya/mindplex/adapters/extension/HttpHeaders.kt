package dev.kigya.mindplex.adapters.extension

import io.ktor.http.*

inline val HttpHeaders.XRealIP: String
    get() = "X-Real-IP"
