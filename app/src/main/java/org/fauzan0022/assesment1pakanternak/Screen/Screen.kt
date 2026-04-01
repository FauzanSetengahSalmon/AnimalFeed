package org.fauzan0022.assesment1pakanternak.Screen

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object History: Screen("historyScreen")
}