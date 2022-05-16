package com.example.learningcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learningcompose.ui.screens.HomeScreen
import com.example.learningcompose.ui.screens.ProfileScreen
import com.example.learningcompose.ui.screens.SettingScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) { HomeScreen() }
        composable(route = BottomBarScreen.Profile.route) { ProfileScreen() }
        composable(route = BottomBarScreen.Settings.route) { SettingScreen() }
    }
}