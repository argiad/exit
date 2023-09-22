package com.steegler.exit

import android.util.Log
import com.steegler.exit.data.entity.RateEntity
import com.steegler.exit.data.remote.Rate
import com.steegler.exit.data.remote.RateUpdateResponse
import com.steegler.exit.data.remote.RatesResponse
import kotlinx.serialization.json.Json

class ResourceHelper {
    companion object {

        val listOfRates by lazy {
            try {
                val fileContent = loadResFile("rates.json")
                Json.decodeFromString<RatesResponse>(fileContent).rates
            } catch (e: Exception) {
                Log.e("Error", e.localizedMessage)
                listOf()
            }
        }

        val listOfRateEntities by lazy {
            try {
                val fileContent = loadResFile("rates.json")
                Json.decodeFromString<RatesResponse>(fileContent).rates.map { RateEntity(it) }
            } catch (e: Exception) {
                Log.e("Error", e.localizedMessage)
                listOf()
            }
        }

        val correctRateItem: Rate? by lazy {
            try {
                val fileContent = loadResFile("correctOneRateResponse.json")
                Json.decodeFromString<RateUpdateResponse>(fileContent).rate
            } catch (e: Exception) {
                Log.e("Error", e.localizedMessage)
                null
            }
        }

        val correctRateEntityItem: RateEntity? by lazy {
            try {
                val fileContent = loadResFile("correctOneRateResponse.json")
                val rate = Json.decodeFromString<RateUpdateResponse>(fileContent).rate
                RateEntity(rate)
            } catch (e: Exception) {
                Log.e("Error", e.localizedMessage)
                null
            }
        }

        fun getEntityItem(id: String): RateEntity? {
            return listOfRateEntities.firstOrNull { it.id == id }
        }

        fun loadResFile(fileName: String): String {
            return this::class.java.classLoader!!.getResource(fileName).readText()
        }
    }
}