package com.steegler.exit.network

import com.steegler.exit.data.remote.Rate


data class ResultState(

    val isLoading: Boolean = true,
    val itemsList: List<Rate> = emptyList(),
    val error: String = "",
)
