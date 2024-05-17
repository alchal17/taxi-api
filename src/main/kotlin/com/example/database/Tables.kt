package com.example.database

import com.example.models.Drivers
import com.example.models.Orders
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun createTables() {
    transaction {
        SchemaUtils.create(Drivers)
        SchemaUtils.create(Orders)
    }
}