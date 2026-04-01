package org.fauzan0022.assesment1pakanternak

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.fauzan0022.assesment1pakanternak.Screen.AboutScreen
import org.fauzan0022.assesment1pakanternak.Screen.MainScreen
import org.fauzan0022.assesment1pakanternak.Screen.Screen

@Composable
fun setupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen()
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
    }
}