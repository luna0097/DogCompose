package com.example.proyectofinal.dataLayer.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="perros")
data class DogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "breed")
    val breed: String = "",
    @ColumnInfo(name = "image")
    val img: String = ""
)