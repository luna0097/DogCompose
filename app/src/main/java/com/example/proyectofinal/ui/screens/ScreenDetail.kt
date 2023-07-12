package com.example.proyectofinal.uiLayer.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.proyectofinal.R
import com.example.proyectofinal.dataLayer.model.Dog
import com.example.proyectofinal.dataLayer.repositories.db.DogDao
import com.example.proyectofinal.dataLayer.repositories.db.DogRepositoryRoom
import com.example.proyectofinal.dataLayer.repositories.db.DogRoomDb
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.ui.componens.OutlinedText
import com.example.proyectofinal.ui.navegacion.Routes
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Composable
fun ScreenDetail(
    breed: String,
    viewModel: ViewModelDog
) {

    val dogImgState: MutableState<List<String>> = remember {
        mutableStateOf(emptyList())
    }
    viewModel.dogsDetail.observeForever {
        dogImgState.value = it
    }

    val favoriteDogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    viewModel.roomDogBreeds.observeForever {
        favoriteDogState.value = validateFavorite(breed, viewModel.roomDogBreeds.value?.toList() ?: emptyList())
    }

    viewModel.getDogBreedImages(breed = breed)
    viewModel.getRoomAllDogs()



    ProyectoFinalTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {
                            Text(text = "Raza:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Text(text = breed, fontSize = 24.sp)
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End) {

                            Image(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable {
                                        val dog = Dog(breed, dogImgState.value[0])
                                        if (favoriteDogState.value) {
                                            deleteFavorite(dog, viewModel)
                                        } else {
                                            insertFavorite(dog, viewModel)
                                        }
                                    },
                                painter =
                                if (favoriteDogState.value)
                                    painterResource(id = R.drawable.baseline_favorite_24)
                                else
                                    painterResource(id = R.drawable.baseline_favorite_border_24),
                                contentDescription = "favorito",
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (dogImgState.value?.isNotEmpty()==true){

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                            AsyncImage(
                                model = dogImgState.value[0],
                                contentDescription = "Translated description of what the image contains",
                                modifier = Modifier.size(300.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                    ) {

                        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                                AsyncImage(
                                    model = dogImgState.value[1],
                                    contentDescription = "Translated description of what the image contains",
                                    modifier = Modifier.size(140.dp)
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                AsyncImage(
                                    model = dogImgState.value[2],
                                    contentDescription = "Translated description of what the image contains",
                                    modifier = Modifier.size(140.dp)
                                )
                            }
                        }
                    }


                }

            }
        }
    }
}

fun insertFavorite(dog : Dog, viewModel: ViewModelDog){
    viewModel.insertRoomDog(dog)
}

fun deleteFavorite(dog : Dog, viewModel: ViewModelDog){
    viewModel.deleteRoomDog(dog)
}

fun validateFavorite(breed: String, favoritesDogs : List<String>): Boolean{
    return favoritesDogs.contains(breed)
}


