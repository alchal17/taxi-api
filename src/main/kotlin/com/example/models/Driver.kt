package com.example.models

import org.jetbrains.exposed.dao.id.IntIdTable
import kotlinx.serialization.Serializable

enum class Languages {
    ENGLISH,
    SPANISH,
    MANDARIN,
    FRENCH,
    GERMAN,
    ARABIC,
    HINDI,
    BENGALI,
    PORTUGUESE,
    UKRAINIAN
}



object Drivers : IntIdTable() {
    val username = varchar("username", 50)
    val password = varchar("password", 50)
    val email = varchar("email", 30)
    val fullName = varchar("full_name", 150)
    val phoneNumber = varchar("phone_number", 20)
    val languages = varchar("languages", 300)
}

@Serializable
data class Driver(
    val id: Int? = null,
    val username: String,
    val password: String,
    val email: String,
    val fullName: String,
    val phoneNumber: String,
    val languages: List<Languages>,
) : Model