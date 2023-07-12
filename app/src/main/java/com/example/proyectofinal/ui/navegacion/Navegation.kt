package com.example.proyectofinal.ui.navegacion

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.domainLayer.viewModels.ViewModelDog
import com.example.proyectofinal.uiLayer.screens.ScreenDogs
import com.example.proyectofinal.uiLayer.screens.ScreenFavorite
import com.example.proyectofinal.uiLayer.screens.ScreenDetail


sealed class Routes(val route: String) {
    object Random : Routes("random")
    object Detail : Routes("detail")
    object Favorite : Routes("favorite")
}

@Composable
fun Navigation(viewModelDog: ViewModelDog, context : Context) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Random.route) {

        composable(Routes.Random.route) {
            ScreenDogs(
                navController= navController,
                viewModel = viewModelDog)
        }

        composable(
            "${Routes.Detail.route}/{breed}",
            arguments = listOf(navArgument("breed") { type = NavType.StringType })
        ) {
            val breed = it.arguments?.getString("breed") ?: ""
            ScreenDetail(
                breed,
                viewModel = viewModelDog,
                context
            )
        }

        composable(Routes.Favorite.route) {
            ScreenFavorite(
                navController= navController,
                viewModel = viewModelDog
            )
        }
    }
}