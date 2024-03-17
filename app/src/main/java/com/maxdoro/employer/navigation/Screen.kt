package com.maxdoro.employer.navigation

sealed class Screen(val route: String) {
    object Search : Screen("searchScreen")
    object Details : Screen("detailsScreen")
}