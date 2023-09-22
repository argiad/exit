package com.steegler.exit.domain.repository

import androidx.lifecycle.LiveData
import com.steegler.exit.data.entity.RateEntity

interface RatesRepo {
    fun getAllItemsStream(): LiveData<List<RateEntity>>

    fun getItemStream(id: String): RateEntity?

    fun insertAll(vararg messages: RateEntity)


}