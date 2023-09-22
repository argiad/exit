package com.steegler.exit.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateUpdateResponse(
    @SerialName("data")
    val rate: Rate,
    @SerialName("timestamp")
    val timestamp: Long
)
