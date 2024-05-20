package com.example.database

import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    Database.connect(
        url = "",
        driver = "org.postgresql.Driver",
    )
}
