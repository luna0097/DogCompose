package com.example.proyectofinal.dataLayer.repositories.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinal.dataLayer.entities.DogEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface DogDao {
    @Insert()
    fun insert(dog: DogEntity)

    @Query("SELECT * FROM perros")
    fun getAll(): Flow<List<DogEntity>>

    @Query("Delete from perros WHERE breed = :breed ")
    suspend fun delete(breed: String)
}