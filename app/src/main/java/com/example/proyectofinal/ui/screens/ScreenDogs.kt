package com.example.proyectofinal.uiLayer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectofinal.dataLayer.model.Breeds
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.ui.componens.DogItemCard
import com.example.proyectofinal.ui.componens.OutlinedText
import com.example.proyectofinal.ui.navegacion.Routes
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme


@Composable
fun ScreenDogs(navController: NavHostController, viewModel: ViewModelDog) {

    val dogsBreedsState: MutableState<List<String>?> = remember {
        mutableStateOf(viewModel.dogBreeds.value)
    }
    //Observador cuando la lista de perros cambia
    viewModel.dogBreeds.observeForever {
        dogsBreedsState.value = it
    }

    viewModel.getDogBreeds()

    ProyectoFinalTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "ScreenDogs", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.End) {
                        Button(onClick = { navController.navigate(Routes.Favorite.route) }, modifier = Modifier.wrapContentWidth()) {
                            Text(text = "Favoritos")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(dogsBreedsState.value?.toList()?: arrayListOf()) {
                        DogItemCard(it, navController)
                        //DogItemCard("Calupho")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewScreenDogs(){
    ScreenDogs(
        navController = NavHostController(LocalContext.current),
        viewModel = ViewModelDog()
    )
}
