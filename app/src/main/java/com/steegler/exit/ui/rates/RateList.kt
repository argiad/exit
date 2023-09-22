package com.steegler.exit.ui.rates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.steegler.exit.data.entity.RateEntity


@ExperimentalMaterial3Api
@Composable
fun RateListScreen(
    viewModel: RateListViewModel = hiltViewModel(),
    selectedRate: (String) -> Unit
) {
    val rates by viewModel.rates.observeAsState()
    val llState = rememberLazyListState(initialFirstVisibleItemIndex = rates?.size ?: 0)
    LaunchedEffect(Unit) {
        viewModel.requestData()
    }
    // Just for fun .....
    LaunchedEffect(rates) {
        llState.scrollToItem(rates?.size ?: 0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Rates list")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = llState,
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(Color.Transparent)
                .padding(innerPadding)
                .padding(8.dp, end = 8.dp)
        ) {
            rates?.let { list: List<RateEntity> ->
                items(list) {
                    Column(modifier = Modifier.selectable(selected = false, onClick = { selectedRate(it.id) })) {

                        Row {
                            Icon(
                                imageVector = if (it.type == "crypto") Icons.Rounded.ExitToApp else Icons.TwoTone.DateRange,
                                contentDescription = null
                            )
                            Spacer(
                                modifier = Modifier
                                    .size(1.dp)
                            )
                            Text(
                                text = "${it.symbol}", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Spacer(
                                modifier = Modifier
                                    .size(1.dp)
                            )
                            Text(
                                text = "ID: ${it.id}", modifier = Modifier
                                    .fillMaxWidth()
                                    .height(28.dp)
                                    .weight(5f)
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(color = Color.DarkGray)
                        )
                    }
                }

            }
        }
    }
}
