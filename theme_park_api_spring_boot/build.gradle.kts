//  gradle publishToMavenLocal --no-configuration-cache
plugins {
    //`java-library`
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    `maven-publish`
}

repositories{
    maven { url = uri("https://repo.spring.io/milestone") }
    mavenCentral()
    mavenLocal()
}

group = "com.fideljose"
version = "1.1.0-SNAPSHOT"

// Configuración específica para Spring Boot
springBoot {
    mainClass.set("com.fideljose.theme_park_api.ThemeParkApplication") // Reemplaza con tu clase principal
}
// Deshabilita el JAR normal si no lo necesitas
//tasks.jar {
//    enabled = false
//}
// Configura el JAR ejecutable de Spring Boot
tasks.bootJar {
    archiveClassifier.set("boot")
    manifest {
        attributes("Start-Class" to "com.fideljose.theme_park_api.ThemeParkApplication")
    }
}

dependencies{
    // 1. Primero importa el BOM (control central de versiones)
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.0")) // BOM

    implementation("org.springframework.boot:spring-boot-starter-web")
}

java{
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
tasks.named<Test>("test") {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            // Publica el BootJar como artefacto principal (archivo ...-boot.jar)
            artifact(tasks.bootJar)
        }
    }
    repositories {
        maven {
            url = uri("https://kslsbvlsbvlsvflvfjbjfbvfjbvjb.amazon.com/maven/demo")
            credentials {
                username = "aws"
                password = System.getenv("ARTEFACT_AUTH_TOKEN")
            }
        }
    }
}