import org.gradle.internal.impldep.com.amazonaws.event.DeliveryMode

//plugins {
//    `java-library`
//    base
//}
//version = 1.3
//repositories{
//    mavenCentral()
//    google()
//    maven {
//        // repo local
//        url = uri("file://${project.rootDir}/libs/local-repo")
//        // repo maven propio
//        url = uri("https://blablablablablablablablabla")
//        credentials {
//            // mejor settearlos en 'gradle.properties' o refenciar variables de entorno
//            username = project.findProperty("repoUser") as String? ?: ""
//            password = project.findProperty("repoPass") as String? ?: ""
//        }
//    }
//}
//
//dependencies{
//    // https://mvnrepository.com/artifact/commons-beanutils/commons-beanutils
//    implementation("commons-beanutils:commons-beanutils:1.11.0")
//}

plugins {
    java
    application
}
repositories{
    mavenCentral()
}
application {
    mainClass.set("com.fideljose.themepark.RideStatusService") // Reemplaza con tu clase principal
}
tasks.named<Jar>("jar") {
    manifest {
        attributes("Main-Class" to "com.fideljose.themepark.RideStatusService")
    }
}
object Versions {
    const val JUNIT_JUPITER_VERSION = "5.7.2"
}
dependencies{
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT_JUPITER_VERSION}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.JUNIT_JUPITER_VERSION}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT_JUPITER_VERSION}")
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    //include("**/RideStatusServiceTest*")
    //exclude("**/RideStatusServiceTest*")
}
tasks.test {
    useJUnitPlatform()
}
//*** config tasks from plugins
tasks.named<JavaCompile>("compileJava") {
    options.isVerbose = true
}
tasks.named<ProcessResources>("processResources"){
   include("**/*.txt")
    //exclude("**/*.txt")
}
tasks.named<Jar>("jar") {
    archiveFileName = "customeFileName.jar"
}
//*** fin

//*** integration test
testing {
    suites {
        register<JvmTestSuite>("integrationTest") {
            useJUnitJupiter()
            dependencies {
                implementation(project())
            }
        }
    }
}
tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}

java{
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}
