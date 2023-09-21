package com.steegler.exit.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatesResponse(
    @SerialName("data")
    val rates: List<Rate>,
    @SerialName("timestamp")
    val timestamp: Long
)