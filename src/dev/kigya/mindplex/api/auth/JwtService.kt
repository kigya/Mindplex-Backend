package dev.kigya.mindplex.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtService(secret: String, private val issuer: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(subject: String): String =
        JWT.create()
            .withIssuer(issuer)
            .withSubject(subject)
            .withIssuedAt(Date())
            .sign(algorithm)
}
