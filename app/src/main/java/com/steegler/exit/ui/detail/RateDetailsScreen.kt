package com.steegler.exit.ui.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateDetailScreen(
    rateID: String,
    navBack: () -> Unit,
    viewModel: RateDetailsViewModel = hiltViewModel()
) {
    val item = viewModel.rate.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchRate(rateID)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navBack) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Item ${item.value?.id} details")
                }
            )
        }
    ) { innerPadding ->
        if (item.value != null) {
            RateItem(item = item.value!!, innerPadding = innerPadding)
        }
    }
}
