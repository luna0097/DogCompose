package com.example.proyectofinal.dataLayer.repositories.db

import androidx.annotation.WorkerThread
import com.example.proyectofinal.dataLayer.entities.DogEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class DogRepositoryRoom(private val dogDao: DogDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val allDogs: Flow<List<DogEntity>> = dogDao.getAll()

    @WorkerThread
    fun insert(dog: DogEntity){
        dogDao.insert(dog)
    }


    suspend fun delete(breed: String){
        dogDao.delete(breed)
    }
}