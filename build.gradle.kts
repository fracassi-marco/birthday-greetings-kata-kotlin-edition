import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "org.kata"
version = "1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.mail:mail:1.4")
    testImplementation(kotlin("test"))
    testImplementation("dumbster:dumbster:1.6")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}