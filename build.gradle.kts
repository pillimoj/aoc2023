plugins {
    kotlin("jvm") version "1.9.10"
    application
}

val aocMainClass = "aoc.MainKt"

group = "aoc"
version = "1.0-SNAPSHOT"

application {
    mainClass.set(aocMainClass)
}

kotlin.sourceSets {
    main { kotlin.srcDir("src") }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
}
