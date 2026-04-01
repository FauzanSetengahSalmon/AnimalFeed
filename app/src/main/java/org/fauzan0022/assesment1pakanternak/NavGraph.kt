package org.fauzan0022.assesment1pakanternak

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.fauzan0022.assesment1pakanternak.Screen.AboutScreen
import org.fauzan0022.assesment1pakanternak.Screen.HistoryScreen
import org.fauzan0022.assesment1pakanternak.Screen.MainScreen
import org.fauzan0022.assesment1pakanternak.Screen.Screen

@Composable
fun setupNavGraph(navController: NavHostController = rememberNavController()) {
    val historyList = remember { mutableStateListOf<String>() }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController, historyList)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.History.route) {
            HistoryScreen(navController, historyList)
        }
    }
}
