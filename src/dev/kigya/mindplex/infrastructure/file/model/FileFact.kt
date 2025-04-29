package dev.kigya.mindplex.infrastructure.file.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileFact(
    @SerialName("fact") val fact: String,
)
