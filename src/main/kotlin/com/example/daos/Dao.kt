package com.example.daos

import com.example.models.Model
import org.jetbrains.exposed.sql.ResultRow

abstract class Dao<T : Model> {
    abstract fun getAll(): List<T>
    abstract fun add(item: T)
    protected abstract fun toEntity(row: ResultRow): T
}