package com.example.proyectofinal.uiLayer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.ui.componens.DogItemCard
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import com.example.proyectofinal.ui.navegacion.Routes


@Composable
fun ScreenFavorite(navController: NavHostController, viewModel: ViewModelDog) {

    val favoriteDogsBreedsState: MutableState<List<String>?> = remember {
        mutableStateOf(viewModel.roomDogBreeds.value)
    }

    viewModel.roomDogBreeds.observeForever {
        favoriteDogsBreedsState.value = it
    }

    viewModel.getRoomAllDogs()

    ProyectoFinalTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "ScreenFavorite", fontSize = 28.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {

                    items(favoriteDogsBreedsState.value?.toList()?: arrayListOf("Calupoh", "Labrador", "Pastor Aleman")) {
                        DogItemCard(it, navController)
                        //DogItemCard("Calupho")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
