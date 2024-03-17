package com.maxdoro.employer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MaxdoroScaffold(
    startScreen: Screen,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            MaxdoroNavHost(
                startDestination = startScreen,
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        },
        modifier = modifier
    )
}
