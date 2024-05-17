package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable

object Orders : IntIdTable() {
    val name = varchar("name", 50)
    val phone = varchar("phone", 20)
    val lat = double("latitude")
    val lng = double("longitude")
    val address = varchar("address", 500)
}

@Serializable
data class Order(
    val id: Int? = null,
    val name: String,
    val phone: String,
    val lat: Double,
    val lng: Double,
    val address: String
) : Model