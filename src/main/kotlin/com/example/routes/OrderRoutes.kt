package com.example.routes

import com.example.daos.OrderDao
import com.example.models.Order
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoutes() {
    route("/order") {
        post("/add") {
            val order = call.receive<Order>()
            OrderDao.add(order)
            call.respond(HttpStatusCode.OK, "Order added")
        }
        get("/all") {
            call.respond(OrderDao.getAll())
        }
    }
}