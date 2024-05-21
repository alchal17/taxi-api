package com.example

import com.example.database.configureDatabase
import com.example.database.createTables
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.*
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType) // Ensure you allow Content-Type header
        allowCredentials = true
        allowHost("localhost:3000") // During development. Do not use this in production.
    }
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    configureDatabase()
    createTables()
    configureSerialization()
    configureRouting()
}
