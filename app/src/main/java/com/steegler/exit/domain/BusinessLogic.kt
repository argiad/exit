package com.steegler.exit.domain

import android.util.Log
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.domain.repository.RatesRepo
import com.steegler.exit.network.NetworkService

interface BusinessLogic {
    suspend fun updateRates()
    suspend fun getUpdatesFor(rateID: String)

    suspend fun getRateBy(rateID: String): RateEntity?

    companion object {
        fun build(repo: RatesRepo, networkService: NetworkService): BusinessLogic {
            return BusinessLogicImpl(repo, networkService)
        }
    }

    private class BusinessLogicImpl constructor(private val repo: RatesRepo, private val networkService: NetworkService) : BusinessLogic {

        override suspend fun updateRates() {

            networkService.ratesRequest { resultState ->
                if (resultState.itemsList.isNotEmpty()) {
                    val list = resultState.itemsList.map { RateEntity(it) }
                    repo.insertAll(*list.toTypedArray())
                } else {
                    Log.e("********", ">>> ${resultState.error}")
                }
            }
        }

        override suspend fun getUpdatesFor(rateID: String) {
            networkService.getUpdatesFor(rateID) { resultState ->
                if (resultState.itemsList.isNotEmpty()) {
                    val list = resultState.itemsList.map { RateEntity(it) }
                    repo.insertAll(*list.toTypedArray())
                    println(list)
                } else {
                    Log.e("********", ">>> ${resultState.error}")
                }
            }
        }

        override suspend fun getRateBy(rateID: String): RateEntity? {
            return repo.getItemStream(rateID)
        }
    }
}