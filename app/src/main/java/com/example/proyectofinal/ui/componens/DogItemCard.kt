package com.example.proyectofinal.ui.componens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.navegacion.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun DogItemCard(breed: String){
fun DogItemCard(breed: String, navHost: NavHostController = NavHostController(LocalContext.current)){

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
        onClick = {navHost.navigate("${Routes.Detail.route}/$breed") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.dog_icon),
                contentDescription = breed
            )
            Spacer(modifier = Modifier.width(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.wrapContentWidth()) {
                    Text(text = "Raza:", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = breed, fontSize = 24.sp)
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = breed,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDogItemCard(){
    //DogItemCard("Calupoh")
    DogItemCard("Calupoh", NavHostController(LocalContext.current))
}