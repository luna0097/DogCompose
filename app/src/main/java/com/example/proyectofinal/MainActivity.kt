package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.dataLayer.repositories.db.DogRepositoryRoom
import com.example.proyectofinal.dataLayer.repositories.db.DogRoomDb
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.domainLayer.viewModels.ViewModelFactory
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import com.example.proyectofinal.ui.navegacion.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    val scope = CoroutineScope(Dispatchers.IO)

    val database by lazy { DogRoomDb.getDatabase(
        context = this,
        scope = scope)
    }

    val repository by lazy { DogRepositoryRoom(database.dogDao()) }

    private val viewModelDog by viewModels<ViewModelDog>{
        ViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(viewModelDog = viewModelDog)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //Navigation(viewModelDog = ViewModelDog(DogRepositoryRoom(database.dogDao())) )
}