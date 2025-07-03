plugins {
  alias(libs.plugins.kotlin.jvm)
  application
}

repositories { mavenCentral() }

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation(libs.junit.jupiter.engine)
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  // These dependency are used by the application.
  implementation(libs.guava)
  implementation("com.github.ajalt.clikt:clikt:5.0.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

java { toolchain { languageVersion = JavaLanguageVersion.of(17) } }

application { mainClass = "org.ktorite.AppKt" }

tasks.named<Test>("test") { useJUnitPlatform() }
