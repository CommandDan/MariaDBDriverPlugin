plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.gradleup.shadow") version "9.2.2"
}

group = "dk.marcusrokatis"
version = "1.0.0" + "-${property("mariaDBVersion")}"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${property("paperApiVersion")}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mariadb.jdbc:mariadb-java-client:${property("mariaDBVersion")}")
}

kotlin {
    jvmToolchain(property("javaVersion").toString().toInt())
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}
