package com.example.fittrack.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "ejercicios")
data class Ejercicio(
    @PrimaryKey
    val nombre: String,
)

