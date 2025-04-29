import org.gradle.jvm.tasks.Jar

val mainClass = "dev.kigya.mindplex.main.ApplicationKt"

tasks.register<Jar>("buildFatJar") {
    group = "build"
    description = "Assembles a fat JAR with all dependencies"
    archiveFileName.set("app-all.jar")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = mainClass
    }

    from({
        configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) }
    })
    with(tasks.jar.get())
}