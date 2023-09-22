package com.steegler.exit.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.steegler.exit.R
import com.steegler.exit.ui.detail.RateDetailScreen
import com.steegler.exit.ui.rates.RateListScreen


abstract sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
) {
    @Composable
    open fun screen(rateID: String, navBack: () -> Unit): Unit {
    }

    @Composable
    open fun screen(selectRate: (rateID: String) -> Unit = {}): Unit {
    }

    @ExperimentalMaterial3Api
    object Rates : Screen("rates_screen", R.string.rates) {
        @Composable
        override fun screen(selectRate: (rateID: String) -> Unit) {
            return RateListScreen(selectedRate = selectRate)
        }
    }

    object Details : Screen("details_screen", R.string.rate) {
        @Composable
        override fun screen(rateID: String, navBack: () -> Unit) {
            return RateDetailScreen(rateID, navBack = navBack)
        }
    }

    companion object {
        const val RATE_ID_KEY = "rateID"
    }
}


//sealed class Screen(
//    val route: String,
//    @StringRes val resourceId: Int,
//    open val screen: @Composable (rateID: Int) -> Unit
//) {
////    class Rates(override val screen: @Composable (rateID: Int, navBack: (() -> Unit)) -> Unit) : Screen("login_screen", R.string.rates, screen = screen)
//    class Rates(override val screen: @Composable (rateID: Int = 0) -> Unit) : Screen("login_screen", R.string.rates, screen = screen)
//    object Details : Screen("details_screen", R.string.rate, screen = { rateID, navBack -> RateDetailScreen(rateID, navBack = navBack) })

//sealed class Screen(
//    val route: String,
//    @StringRes val resourceId: Int,
//    val screen: @Composable () -> Unit
//) {
//
//    class Rates(selectRate: (rateID: Int) -> Unit) : Screen("login_screen", R.string.rates, screen = { RateListScreen(selectedCat = selectRate) })
//    class Details(rateID: Int, navBack: () -> Unit) : Screen("details_screen", R.string.rate, screen = { RateDetailScreen(rateID, navBack = navBack) })
//    companion object {
//        const val RATE_ID_KEY = "rateID"
//    }
//
//}