package com.example.plugins

import com.example.routes.orderRoutes
import com.example.routes.driverRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        driverRoutes()
        orderRoutes()
    }
}
