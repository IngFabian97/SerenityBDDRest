plugins {
    java
    id("net.serenity-bdd.serenity-gradle-plugin") version "4.2.1"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Propiedades del proyecto
val javaVersion = JavaVersion.VERSION_17
val serenityVersion = "4.2.1"
val cucumberVersion = "7.18.1"
val restAssuredVersion = "5.5.0"
val junitVersion = "5.10.2"
val assertJVersion = "3.26.3"
val hamcrestVersion = "3.0"
val lombokVersion = "1.18.34"
val guavaVersion = "33.2.1-jre"
val gsonVersion = "2.11.0"
val jacksonVersion = "2.17.2"
val slf4jVersion = "2.0.13"
val logbackVersion = "1.5.6"

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

dependencies {
    // Serenity BDD Dependencies
    implementation("net.serenity-bdd:serenity-core:$serenityVersion")
    implementation("net.serenity-bdd:serenity-junit5:$serenityVersion")
    implementation("net.serenity-bdd:serenity-screenplay:$serenityVersion")
    implementation("net.serenity-bdd:serenity-screenplay-rest:$serenityVersion")
    implementation("net.serenity-bdd:serenity-single-page-report:$serenityVersion")
    
    testImplementation("net.serenity-bdd:serenity-ensure:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-cucumber:$serenityVersion")

    // REST Assured
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$restAssuredVersion")
    implementation("io.rest-assured:xml-path:$restAssuredVersion")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.vintage:junit-vintage-engine:$junitVersion")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")

    // Cucumber
    implementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")

    // Logging
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Utilities
    implementation("com.google.guava:guava:$guavaVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    // Assertions
    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")

    // Lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    
    // Configuración de paralelización
    maxParallelForks = Runtime.getRuntime().availableProcessors()
    
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = false
    }
    
    systemProperty("restapi.baseurl", project.findProperty("restapi.baseurl") ?: "https://reqres.in/api")
    systemProperty("cucumber.filter.tags", System.getProperty("cucumber.filter.tags") ?: "")
    systemProperty("cucumber.plugin", "io.cucumber.core.plugin.SerenityReporterParallel")
}

// Configuración específica de Serenity
serenity {
    reports = listOf("single-page-html", "json")
}

// Tarea personalizada para tests de integración
tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"
    
    useJUnitPlatform()
    
    // Incluir solo tests de integración
    include("**/*Test.class", "**/Test*.class", "**/*TestSuite.class", "**/When*.class", "**/*Runner.class")
    
    // Configuración de paralelización
    maxParallelForks = (project.findProperty("parallel.tests") as String?)?.toInt() ?: 4
    
    systemProperty("restapi.baseurl", project.findProperty("restapi.baseurl") ?: "https://reqres.in/api")
    
    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
    
    finalizedBy("aggregate")
}

// Asegurar que el reporte de Serenity se genera después de los tests
tasks.named("test") {
    finalizedBy("aggregate")
}

tasks.named("check") {
    dependsOn("integrationTest")
}

// Configuración para el wrapper de Gradle
tasks.wrapper {
    gradleVersion = "8.10.2"
    distributionType = Wrapper.DistributionType.BIN
}
