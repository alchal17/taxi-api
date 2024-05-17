package com.example

import com.example.database.configureDatabase
import com.example.database.createTables
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    configureDatabase()
    createTables()
    configureSerialization()
    configureRouting()
}