import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.3"
	//Detekt
	id("io.gitlab.arturbosch.detekt") version("1.23.0")
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.hrv.mart"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven {
		name = "GitHubPackages"
		url = uri("https://maven.pkg.github.com/hrv-mart/user-library")
		credentials {
			username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
			password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
		}
	}
}
detekt {
	toolVersion = "1.23.0"
	config.setFrom(file("config/detekt/detekt.yml"))
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	// Detekt plugin
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")
	// HRV-Mart
	implementation("com.hrv.mart:user-library:0.0.3")
	implementation("com.hrv.mart:api-call:0.0.3")
	implementation("com.hrv.mart:custom-pageable:0.0.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
configurations.all {
	resolutionStrategy.eachDependency {
		if (requested.group == "org.jetbrains.kotlin") {
			useVersion("1.8.21")
		}
	}
}
