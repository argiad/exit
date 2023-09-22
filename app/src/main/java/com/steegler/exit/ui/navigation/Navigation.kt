package com.steegler.exit.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavActions(navController) }
    Surface(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        Box {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Rates.route
                    ) {
                        composable(
                            Screen.Rates.route
                        ) {
                            Screen.Rates.screen(selectRate = actions.selectedRate)
                        }
                        composable(
                            "${Screen.Details.route}/{${Screen.RATE_ID_KEY}}",
                            arguments = listOf(
                                navArgument(Screen.RATE_ID_KEY) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            Screen.Details.screen(
                                arguments.getString(Screen.RATE_ID_KEY, ""),
                                actions.navBack
                            )
                        }
                    }
                }
            }
        }
    }

}


