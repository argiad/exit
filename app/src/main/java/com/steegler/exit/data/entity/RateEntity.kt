package com.steegler.exit.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.steegler.exit.data.remote.Rate

@Entity
data class RateEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "currencySymbol")
    val currencySymbol: String?,

    @ColumnInfo(name = "rateUsd")
    val rateUsd: String,

    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "type")
    val type: String
) {
    constructor(rate: Rate) : this(id = rate.id, currencySymbol = rate.currencySymbol, rateUsd = rate.rateUsd, symbol = rate.symbol, type = rate.type)
}