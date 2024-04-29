package com.example.dumbpasswordgenerator.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dumbpasswordgenerator.ui.start.StartScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.route
    ) {
        // Start Screen Route
        composable(
            route = Screen.StartScreen.route
        ) {
            StartScreen(navController = navController)
        }

        // Password Screen Route
        composable(
            route = Screen.PasswordScreen.route
        ) {
            PasswordScreen(navController = navController)
        }
    }
}
