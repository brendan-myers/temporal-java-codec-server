plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
}

group = "com.brendan"
version = "0.0.1-SNAPSHOT"
var temporalVersion = "1.22.3"
var javaSDKVersion = "1.22.3"
var otelVersion = "1.30.1"
var otelVersionAlpha = "${otelVersion}-alpha"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.temporal:temporal-spring-boot-starter-alpha:${temporalVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("io.temporal:temporal-sdk:${javaSDKVersion}")
    testImplementation("io.temporal:temporal-testing:${javaSDKVersion}")

	testImplementation("org.junit.jupiter:junit-jupiter:5.7.0") // Adjust version as necessary

	implementation("org.jetbrains:annotations:24.0.0")
}