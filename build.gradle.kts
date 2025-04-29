plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

application {
    mainClass.set("dev.kigya.mindplex.main.ApplicationKt")
}

tasks.shadowJar {
    archiveFileName.set("app-all.jar")
    mergeServiceFiles()
    manifest { attributes["Main-Class"] = "dev.kigya.mindplex.main.ApplicationKt" }
}
