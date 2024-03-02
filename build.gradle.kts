import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
}

group = "com.ptit"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// https://mvnrepository.com/artifact/com.auth0/java-jwt
	implementation("com.auth0:java-jwt:4.4.0")
	// https://mvnrepository.com/artifact/org.springframework.security/spring-security-web
	implementation("org.springframework.security:spring-security-web")
// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-security")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("com.google.code.gson:gson:2.10.1")
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
