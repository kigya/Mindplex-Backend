package dev.kigya.mindplex.api.auth

import dev.kigya.mindplex.domain.usecase.FindUserByIdUseCase
import dev.kigya.mindplex.util.extension.stage
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject

const val JWT_MINDPLEX_AUTH_KEY = "mindplex-jwt"

fun Application.configureAuthentication() {
    val jwtService: JwtService by inject()
    val findUserByIdUseCase: FindUserByIdUseCase by inject()

    install(Authentication) {
        jwt(JWT_MINDPLEX_AUTH_KEY) {
            realm = "mindplex"
            verifier(jwtService.verifier)
            validate { cred: JWTCredential ->
                val uid = cred.payload.subject
                val user = findUserByIdUseCase(stage(), uid)
                if (user != null) JWTPrincipal(cred.payload)
                else null
            }
        }
    }
}
