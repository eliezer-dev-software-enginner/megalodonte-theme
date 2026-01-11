plugins {
    id("java")
    id("maven-publish")
}

group = "megalodonte"
version = "1.0.0-beta"

repositories {
    mavenCentral()
    mavenLocal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    // Dependências de teste (mantidas)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")

    implementation("megalodonte:megalodonte-base:1.0.0-beta")
    implementation("megalodonte:megalodonte-reactivity:1.0.0-beta")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveBaseName.set("megalodonte-theme")

    manifest {
        attributes(
            "Implementation-Title" to "Megalodonte base theme",
            "Implementation-Version" to project.version
        )
    }
}

// Configuração de Publicação (mantida)
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "megalodonte-theme"
        }
    }
}