package com.steegler.exit.domain.repository

import androidx.lifecycle.LiveData
import com.steegler.exit.data.dao.RateDao
import com.steegler.exit.data.entity.RateEntity

class BasicRepository(private val rateDao: RateDao) : RatesRepo {
    override fun getAllItemsStream(): LiveData<List<RateEntity>> = rateDao.getAll()

    override fun getItemStream(id: String): RateEntity? = rateDao.getRateBy(id)
    override fun insertAll(vararg items: RateEntity) = rateDao.insertAll(*items)


}
