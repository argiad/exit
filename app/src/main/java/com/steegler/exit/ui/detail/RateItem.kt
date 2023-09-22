package com.steegler.exit.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.steegler.exit.data.entity.RateEntity

@Composable
fun RateItem(item: RateEntity, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Currency Symbol", modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(text = "${item.currencySymbol}", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "ID", modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(text = "${item.id}", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Rate", modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(text = "${item.rateUsd}", modifier = Modifier.weight(1f))
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Symbol", modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(text = "${item.symbol}", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Type", modifier = Modifier.weight(1f))
            Spacer(
                modifier = Modifier
                    .size(1.dp)
            )
            Text(text = "${item.type}", modifier = Modifier.weight(1f))
        }

    }
}
