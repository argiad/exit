package com.steegler.exit.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rate(
    @SerialName("currencySymbol")
    val currencySymbol: String?,
    @SerialName("id")
    val id: String,
    @SerialName("rateUsd")
    val rateUsd: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("type")
    val type: String
)