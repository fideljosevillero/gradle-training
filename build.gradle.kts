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
}
repositories{
    mavenCentral()
}
tasks.named<Jar>("jar") {
    manifest {
        attributes("Main-Class" to "com.fideljose.themepark.RideStatusService")
    }
}
dependencies{
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
tasks.test {
    useJUnitPlatform()
}