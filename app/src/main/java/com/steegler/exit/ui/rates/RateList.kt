@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)

package com.steegler.exit.ui.rates

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.steegler.exit.data.entity.RateEntity
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun RateListScreen(
    viewModel: RateListViewModel = hiltViewModel(),
    selectedRate: (String) -> Unit
) {
    val rates by viewModel.rates.observeAsState()
    val llState = rememberLazyListState(initialFirstVisibleItemIndex = rates?.size ?: 0)
    val groupedRates by viewModel.groupedRates.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, {
        viewModel.requestData()
    })

    LaunchedEffect(Unit) {
        viewModel.requestData()
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
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                state = llState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(innerPadding)
                    .padding(8.dp, end = 8.dp)
            ) {
                groupedRates.forEach { group ->
                    stickyHeader {
                        GroupHeader(text = "${group.key}".uppercase(Locale.getDefault()))
                    }
                    items(group.value) {
                        RateItem(rate = it) { selectedRate(it.id) }
                    }
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
private fun GroupHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
private fun RateItem(rate: RateEntity, onClick: () -> Unit) {
    Column(modifier = Modifier.selectable(selected = false, onClick = onClick)) {

        Row {
            Icon(
                imageVector = if (rate.type == "crypto") Icons.Rounded.ExitToApp else Icons.TwoTone.DateRange,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(
                text = rate.symbol, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(
                text = "ID: ${rate.id}", modifier = Modifier
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
