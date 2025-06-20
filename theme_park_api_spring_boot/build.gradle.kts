//  gradle publishToMavenLocal --no-configuration-cache
plugins {
    `java-library`
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
version = "0.1.0-SNAPSHOT"

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
tasks.withType<Jar> {
    enabled = true
}
//tasks.register<Jar>("sourceJar") {
//    from(sourceSets.main.get().allJava)
//    archiveClassifier.set("sources")
//}
