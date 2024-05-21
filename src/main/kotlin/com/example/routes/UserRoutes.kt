package com.example.routes

import com.example.daos.DriverDao
import com.example.models.Driver
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.driverRoutes() {
    route("/driver") {
        post("/register") {
            val driver = call.receive<Driver>()
            DriverDao.add(driver)
            call.respond(HttpStatusCode.OK, "User created")
        }

        get("/is_email_unique/email={email}") {
            val email = call.parameters["email"]
            if (email == null) {
                call.respond(HttpStatusCode.BadRequest, "Please enter a valid email")
            } else {
                call.respond(if (DriverDao.isEmailExists(email)) HttpStatusCode.BadRequest else HttpStatusCode.OK)
            }
        }
        get("/is_driver_name_unique/driver_name={driver_name}") {
            val driverName = call.parameters["driver_name"]
            if (driverName == null) {
                call.respond(HttpStatusCode.BadRequest, "Please enter a valid name")
            } else {
                call.respond(if (DriverDao.isDriverExists(driverName)) HttpStatusCode.BadRequest else HttpStatusCode.OK)
            }
        }
        get("/login/email={email}/password={password}") {
            val email = call.parameters["email"]
            val password = call.parameters["password"]
            println("${email}, $password")
            if (email != null && password != null) {
                if (DriverDao.isDriverExists(email = email, password = password)) {
                    call.respond("success")
                } else {
                    call.respond("fail")
                }
            }
        }
        get("/all") {
            call.respond(DriverDao.getAll())
        }
    }
}