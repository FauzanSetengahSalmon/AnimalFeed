package org.fauzan0022.assesment1pakanternak

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.fauzan0022.assesment1pakanternak.Screen.AboutScreen
import org.fauzan0022.assesment1pakanternak.Screen.MainScreen
import org.fauzan0022.assesment1pakanternak.Screen.Screen
import org.fauzan0022.assesment1pakanternak.Screen.TipsScreen

@Composable
fun setupNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Tips.route) {
            TipsScreen(navController)
        }
    }
}
