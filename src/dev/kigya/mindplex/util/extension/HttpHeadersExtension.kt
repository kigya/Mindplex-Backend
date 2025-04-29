package dev.kigya.mindplex.util.extension

import io.ktor.http.*

inline val HttpHeaders.XRealIP: String
    get() = "X-Real-IP"

inline val HttpHeaders.XStage: String
    get() = "X-Stage"
