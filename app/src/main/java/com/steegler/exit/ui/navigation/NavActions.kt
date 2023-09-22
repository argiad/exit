package com.steegler.exit.ui.navigation

import androidx.navigation.NavHostController


class NavActions(
    navController: NavHostController
) {
    val selectedRate: (String) -> Unit = { rateID: String ->
        navController.navigate("${Screen.Details.route}/$rateID")
    }
    val navBack: () -> Unit = {
        navController.navigateUp()
    }
}