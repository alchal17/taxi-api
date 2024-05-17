package com.example.database

import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    Database.connect(
        url = "jdbc:postgresql://ha.bigline.net:5432/dbpropro2023?user=pzpi219&password=nure.pzpi21-9&timezone=UTC",
        driver = "org.postgresql.Driver",
    )
}
