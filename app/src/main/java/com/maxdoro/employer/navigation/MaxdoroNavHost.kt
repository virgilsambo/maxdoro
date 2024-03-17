package com.maxdoro.employer.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxdoro.employer.ui.screen.SearchScreen

@Composable
fun MaxdoroNavHost(
    modifier: Modifier = Modifier,
    startDestination: Screen = Screen.Search,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier.background(Color.White)
    ) {
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
    }
}