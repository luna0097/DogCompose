package com.example.proyectofinal.dataLayer.dataSource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinal.dataLayer.entities.DogEntity
import com.example.proyectofinal.dataLayer.dataSource.room.daos.DogDao
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [DogEntity::class],
    version = 2
) abstract class DogRoomDb : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {

        @Volatile
        private var INSTANCE: DogRoomDb? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DogRoomDb {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogRoomDb::class.java,
                    "dog.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}