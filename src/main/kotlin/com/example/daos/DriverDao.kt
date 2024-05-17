package com.example.daos

import com.example.models.Driver
import com.example.models.Drivers
import com.example.models.Languages
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object DriverDao : Dao<Driver>() {
    override fun getAll(): List<Driver> {
        return transaction {
            Drivers.selectAll().map { toEntity(it) }
        }
    }

    override fun toEntity(row: ResultRow): Driver {
        val languageString = row[Drivers.languages]
        val languages = languageString.split(",").map { Languages.valueOf(it) }
        return Driver(
            id = row[Drivers.id].value,
            username = row[Drivers.username],
            password = row[Drivers.password],
            email = row[Drivers.email],
            fullName = row[Drivers.fullName],
            phoneNumber = row[Drivers.phoneNumber],
            languages = languages
        )
    }


    override fun add(item: Driver) {
        transaction {
            Drivers.insert {
                it[username] = item.username
                it[password] = item.password
                it[email] = item.email
                it[fullName] = item.fullName
                it[phoneNumber] = item.phoneNumber
                it[languages] = item.languages.joinToString(",")
            }
        }
    }

    fun isDriverExists(username: String, password: String): Boolean {
        return transaction {
            Drivers.select { (Drivers.username eq username) and (Drivers.password eq password) }.count() > 0
        }
    }

    fun isDriverExists(username: String): Boolean {
        return transaction { Drivers.select { (Drivers.username eq username) }.count() > 0 }
    }

    fun isEmailExists(email: String): Boolean {
        return transaction { Drivers.select { Drivers.email eq email }.count() > 0 }
    }
}