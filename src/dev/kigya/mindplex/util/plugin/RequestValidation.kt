package dev.kigya.mindplex.util.plugin

import dev.kigya.mindplex.api.dto.request.ScoreUpdateRequest
import dev.kigya.mindplex.api.dto.request.UserPostRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

private const val URL_REGEX =
    "^(https?)://[\\w\\-]+(\\.[\\w\\-]+)+([/?#][^\\s]*)?\$"

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<UserPostRequest> { dto ->
            if (dto.token.isBlank()) {
                ValidationResult.Invalid("Token must not be blank")
            } else {
                ValidationResult.Valid
            }
            if (dto.displayName.isBlank()) {
                ValidationResult.Invalid("DisplayName must not be blank")
            } else {
                ValidationResult.Valid
            }
            if (dto.avatarUrl != null && !dto.avatarUrl.matches(Regex(URL_REGEX))) {
                ValidationResult.Invalid("Invalid avatar url")
            } else {
                ValidationResult.Valid
            }
        }
        validate<ScoreUpdateRequest> { dto ->
            if (dto.delta <= 0) {
                ValidationResult.Invalid("Delta must be positive")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
