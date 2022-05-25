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
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("dumbster:dumbster:1.6")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}