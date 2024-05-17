package com.example.daos

import com.example.models.Order
import com.example.models.Orders
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object OrderDao : Dao<Order>() {
    override fun getAll(): List<Order> {
        return transaction { Orders.selectAll().map { toEntity(it) } }
    }

    override fun toEntity(row: ResultRow): Order {
        return Order(
            id = row[Orders.id].value,
            name = row[Orders.name],
            phone = row[Orders.phone],
            lat = row[Orders.lat],
            lng = row[Orders.lng],
            address = row[Orders.address]
        )
    }

    override fun add(item: Order) {
        transaction {
            Orders.insert {
                it[name] = item.name
                it[phone] = item.phone
                it[lat] = item.lat
                it[lng] = item.lng
                it[address] = item.address
            }
        }
    }
}