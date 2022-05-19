package com.example.learningcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.learningcompose.ui.screens.*

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(navController = navHostController)
        }
        composable(
            route = Screen.DetailParam.route,
            arguments = listOf(
                navArgument(DETAIL_PARAM_SCREEN_ARG_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(DETAIL_PARAM_SCREEN_ARG_ID)
            DetailParamScreen(navController = navHostController, id = id)
        }
    }
}