package dev.kigya.mindplex.util.plugin

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.*
import java.io.File

fun Application.configureFirebase() {
    val secretFilePaths = listOf(
        "/etc/secrets/mindplex-firebase-secret.json",
        "./mindplex-firebase-secret.json"
    )

    val stream = secretFilePaths
        .map(::File)
        .firstOrNull(File::exists)
        ?.inputStream()
        ?: object {}.javaClass.getResourceAsStream("/mindplex-firebase-secret.json")
        ?: error("Firebase service account JSON not found in any of $secretFilePaths or on classpath")

    val credentials = GoogleCredentials.fromStream(stream)
    val options = FirebaseOptions.builder()
        .setCredentials(credentials)
        .build()

    if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options)
    }
}
