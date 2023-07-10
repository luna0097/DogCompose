package com.example.proyectofinal.uiLayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.proyectofinal.R
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.ui.componens.OutlinedText
import com.example.proyectofinal.ui.navegacion.Routes
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme

@Composable
fun ScreenDetail(breed: String, navController: NavHostController, viewModelDog: ViewModelDog) {
    var isFavorite by remember{ mutableStateOf(false) }
    ProyectoFinalTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                            modifier = Modifier.size(32.dp).clickable { isFavorite = !isFavorite },
                            painter =
                            if (isFavorite)
                                painterResource(id = R.drawable.baseline_favorite_24)
                            else
                                painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription = "favorito",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.calupoh),
                        contentDescription = "Calupoh"
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))
                Column(horizontalAlignment = Alignment.Start) {

                    Text(text = "Caracteristicas", fontSize = 24.sp)

                    Spacer(modifier = Modifier.height(40.dp))
                    Row() {
                        Text(text = "Tamaño:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "90 cm")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row() {
                        Text(text = "Peso:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "25 - 35 kg")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row() {
                        Text(text = "Expectativa de vida:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "15 años")
                    }



                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenSearch(){
    ScreenDetail(
        "Calupoh",
        navController = NavHostController(LocalContext.current),
        viewModelDog = ViewModelDog()
    )
}

